/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 drakeet (drakeet.me@gmail.com)
 * http://drakeet.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.lib.crash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.lib.crash.ui.CatchActivity;
import com.lib.library.utils.PackageManagerUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;

import static com.lib.library.utils.PackageManagerUtils.getApplicationName;


/**
 * Created by drakeet(http://drakeet.me)
 * Date: 8/31/15 22:35
 */
public class CrashWoodpecker implements UncaughtExceptionHandler {

    private static final PatchMode DEFAULT_MODE = PatchMode.SHOW_LOG_PAGE;

    private volatile UncaughtExceptionHandler originDefaultHandler;
    private volatile UncaughtExceptionInterceptor interceptor;
    private volatile boolean crashing;

    private Context applicationContext;
    private Class mClass;
    /* For highlight */
    private ArrayList<String> keys;
    private PatchMode mode;
    private String patchDialogMessage;
    @SuppressLint("StaticFieldLeak")
    private static CrashWoodpecker instance;


    private CrashWoodpecker() {
        this.crashing = false;
        this.keys = new ArrayList<>();
        this.mode = DEFAULT_MODE;
    }


    public static CrashWoodpecker instance() {
        if (instance == null) {
            instance = new CrashWoodpecker();
        }
        return instance;
    }

    public void flyTo(Context context) {
        this.applicationContext = context.getApplicationContext();
        initContextResources();
        if (!Checks.isWoodpeckerRunning(context)) {
            turnOnHandler();
        }
    }

    public CrashWoodpecker setClass(Class classs) {
        this.mClass = classs;
        return this;
    }

    /**
     * 打开异常log窗体
     * @param throwable 异常信息
     */
    private void startCatchActivity(Throwable throwable) {
        String traces = getStackTrace(throwable);
        Intent intent = new Intent();
        intent.setClass(applicationContext, CatchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String[] strings = traces.split("\n");
        String[] logs = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            logs[i] = strings[i].trim();
        }
        intent.putStringArrayListExtra(CatchActivity.EXTRA_HIGHLIGHT_KEYS, keys);
        intent.putExtra(CatchActivity.EXTRA_APPLICATION_NAME,
                getApplicationName(applicationContext));
        intent.putExtra(CatchActivity.EXTRA_CRASH_LOGS, logs);
        intent.putExtra(CatchActivity.EXTRA_CRASH_4_LOGCAT, Log.getStackTraceString(throwable));
        applicationContext.startActivity(intent);
    }

    /**
     *
     * @param throwable 异常信息
     * @return 返回文本
     */
    private String getStackTrace(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        return writer.toString();
    }

    /**
     * 赋值模式，是打开异常日志，还是弹窗提示
     * @param mode
     * @return
     */
    public CrashWoodpecker setPatchMode(PatchMode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * 赋值信息内容
     *
     * @param message 信息内容
     * @return 返回本身
     */
    public CrashWoodpecker setPatchDialogMessage(String message) {
        this.patchDialogMessage = message;
        return this;
    }

    /**
     * 赋值信息内容
     *
     * @param messageResId 信息id
     * @return 返回本身
     */
    public CrashWoodpecker setPatchDialogMessage(int messageResId) {
        this.patchDialogMessage = applicationContext.getString(messageResId);
        return this;
    }


    private void initContextResources() {
        this.keys.add(this.applicationContext.getPackageName());
        try {
            PackageInfo info = applicationContext.getPackageManager()
                    .getPackageInfo(applicationContext.getPackageName(), 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void turnOnHandler() {
        UncaughtExceptionHandler originDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        if (this != originDefaultHandler) {
            this.originDefaultHandler = originDefaultHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }


    /**
     * 处理弹出窗口
     *
     * @param throwable      异常信息
     * @param message 手机的信息
     * @return 出现异常就返回false继续捕获
     */
    private boolean handleException(Throwable throwable, ArrayList<String> message) {
        try {
            if (mode == PatchMode.SHOW_LOG_PAGE) {
                startCatchActivity(throwable);
            } else if (mode == PatchMode.SHOW_DIALOG_TO_OPEN_URL) {
                showPatchDialog(throwable, message);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 弹出窗口
     *
     * @param ex      异常信息
     * @param message 手机的信息
     */
    private void showPatchDialog(Throwable ex, ArrayList<String> message) {

        StringBuilder exStr = new StringBuilder();

        exStr.append(ex.toString()).append("\r\n");

        if (ex.getCause() != null) {
        }
        for (StackTraceElement item : ex.getCause().getStackTrace()) {
            exStr.append(item.toString()).append("\r\n");
        }
        if (ex.getMessage() != null) {
            exStr.append(ex.getMessage()).append("\r\n");
        }
        for (StackTraceElement item : ex.getStackTrace()) {
            exStr.append(item.toString()).append("\r\n");
        }

        if (message != null)
            for (String item : message) {
                exStr.append(item).append("\r\n");
            }

        Intent intent = BasePatchDialogActivity.newIntent(
                applicationContext,
                getApplicationName(applicationContext),
                patchDialogMessage, exStr.toString(), message.get(0).split("：")[1], mClass);
        applicationContext.startActivity(intent);
    }

    /**
     * 重写捕获异常
     *
     * @param thread    线程
     * @param throwable 异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        // Don't re-enter, avoid infinite loops if crash-handler crashes.
        if (crashing) {
            return;
        }
        crashing = true;

        ArrayList<String> message = PackageManagerUtils.collectDeviceInfo(applicationContext);

        final UncaughtExceptionInterceptor interceptor = this.interceptor;
        // before开放接口
        if (interceptor != null && interceptor.onBeforeHandlingException(throwable, message)) {
            return;
        }

        boolean success = handleException(throwable, message);

        // after结束接口
        if (interceptor != null && interceptor.onAfterHandlingException(throwable, message)) {
            return;
        }

        if (!success) {
            if (originDefaultHandler != null) {
                originDefaultHandler.uncaughtException(thread, throwable);
            }
        }
        byeByeLittleWood();
    }


    /**
     * For setting more highlight keys except package name
     *
     * @param keys highlight keys except package name
     * @return itself
     */
    public CrashWoodpecker withKeys(final String... keys) {
        this.keys.addAll(Arrays.asList(keys));
        return this;
    }

    /**
     * Set uncaught exception interceptor.
     *
     * @param interceptor uncaught exception interceptor.
     * @return itself
     */
    public CrashWoodpecker setInterceptor(UncaughtExceptionInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }

    /**
     * 关闭本身
     */
    private void byeByeLittleWood() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


}

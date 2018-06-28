package com.lib.library.phone;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

public class BaseApplication extends Application {

    private static String baseUrl;
    private static BaseApplication mInstance = null;
    private Toast mToast;

    public static BaseApplication getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Application is not created.");
        }
        return mInstance;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        BaseApplication.baseUrl = baseUrl;
    }

    /**
     * 用于显示同一个toast
     */
    public Toast showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        }
        mToast.setText(message);
        mToast.show();
        return mToast;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initRegisterActivityLifecycleCallbacks(); //回调函数
        initWindow();//初始化屏幕信息并且保存进缓存
        onPreCreateApplication();

    }

    /**
     * 初始化屏幕信息并且保存进缓存
     */
    private void initWindow() {
    }

    /**
     * 回调函数,Activity的回调
     */
    private void initRegisterActivityLifecycleCallbacks() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }
        });
    }

    /**
     * 让子类可以调用的
     */
    protected void onPreCreateApplication() {
    }


}

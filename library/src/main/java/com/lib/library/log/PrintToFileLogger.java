package com.lib.library.log;

import android.util.Log;

import com.lib.library.utils.storage.SdcardUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * 使用方式 PrintToFileLogger printToFileLogger = new PrintToFileLogger(); printToFileLogger.println(ex);
 * PrintToFileLogger是TA框架中打印到sdcard上面的日志类
 * Created by zhongjh on 2014/11/7
 */
public class PrintToFileLogger {

    private String mPath;
    private BufferedWriter mWriter;

    public PrintToFileLogger() {

    }

    /**
     * 打开
     */
    public void open() {
//        try {
//            //外置SD卡
//            File file;
//            //创建text文件
//            SdcardUtils.getLogFile();
//
//            boolean isNew = new File(SdcardUtils.LOG_FOLDER + getCurrentTimeString()
//                    + ".txt").createNewFile();
//            if (!isNew) {
//                //既不存在又创建不了就直接返回
//                return;
//            }
//            file = new File(SdcardUtils.LOG_FOLDER + getCurrentTimeString()
//                    + ".txt");
//            mPath = file.getAbsolutePath();
//            mWriter = new BufferedWriter(new FileWriter(mPath), 2048);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 关闭
     */
    public void close() {
        try {
            mWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTimeString() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        return simpleDateFormat.format(now);
    }

    public String getPath() {
        return mPath;
    }

    /**
     * 输出文字
     * @param message 输入信息
     */
    public void println(String message) {
//        this.open();
//        this.write(message);
//        Log.e("自定义异常：", message);
//        this.close();
    }

    /**
     * 输出异常和其他信息集合
     *
     * @param ex        异常
     * @param arrayList 信息集合
     */
    public void println(Throwable ex, ArrayList<String> arrayList) {
        this.open();
        this.write(ex.toString());
        writeEX(ex);
        //输出信息集合
        if (arrayList != null)
            for (String item : arrayList) {
                this.write(item + "\r\n");
            }
        this.close();
    }

    /**
     * 输出异常
     *
     * @param ex 异常
     */
    private void writeEX(Throwable ex) {
        if (ex.getCause() != null) {
            this.write(ex.getCause().toString());
            Log.e("PrintToFileLogger", ex.getCause().toString());
            for (StackTraceElement item : ex.getCause().getStackTrace()) {
                this.write(item.toString());
                Log.e("PrintToFileLogger", item.toString());
            }
        }
        if (ex.getMessage() != null) {
            this.write(ex.getMessage());
            Log.e("PrintToFileLogger", ex.getMessage());
        }
        for (StackTraceElement item : ex.getStackTrace()) {
            this.write(item.toString());
            Log.e("PrintToFileLogger", item.toString());
        }
    }

    /**
     * 输出信息
     * @param message 信息
     */
    private void write(String message) {
        try {
            mWriter.write(message + "\r\n");
            mWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

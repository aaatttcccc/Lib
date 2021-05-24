package com.lib.library.utils.storage;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 用于文件管理的公共类
 * on 2017/5/30.
 */
public class SdcardUtils {

    private static SdcardUtils mSdcardUtils;

    public static synchronized SdcardUtils instance() {
        if (mSdcardUtils == null) {
            mSdcardUtils = new SdcardUtils();
        }
        return mSdcardUtils;
    }

    /**
     * 获取日志文件夹
     * @param context 上下文
     * @return 返回日志文件夹
     */
    public static File getLogFile(Context context) {
        return context.getExternalFilesDir("log");
    }

    /**
     * sd卡初始化
     * app包文件根目录下log文件
     */
    public void initSdcard(Context context) {
        if (!hasSDCard()) {
            return;
        }
        File logFile = context.getExternalFilesDir("log");
        if (!logFile.exists()) {
            logFile.mkdirs();
        }
    }

    /**
     * @return 返回是否存在SDCard
     */
    public boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

}

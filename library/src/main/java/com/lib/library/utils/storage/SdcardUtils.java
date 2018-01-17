package com.lib.library.utils.storage;

import android.os.Environment;

import com.library.commonFinal.FinalString;

import java.io.File;

/**
 * 用于文件管理的公共类
 * on 2017/5/30.
 */

public class SdcardUtils {

    /**
     * sdcard
     */
    public static final String SDCARD_FOLDER = Environment.getExternalStorageDirectory().toString();

    /**
     * 根目录
     */
    public static final String ROOT_FOLDER = SDCARD_FOLDER + "/" + FinalString.APPNAME + "/";

    /**
     * 日志目录
     */
    public static final String LOG_FOLDER = ROOT_FOLDER + "Log/";

    private static SdcardUtils mSdcardUtils;


    public static synchronized SdcardUtils instance() {
        if (mSdcardUtils == null) {
            mSdcardUtils = new SdcardUtils();
        }
        return mSdcardUtils;
    }

    /**
     * sd卡初始化
     */
    public void initSdcard() {
        if (!hasSDCard())
            return;
        File logFile = new File(LOG_FOLDER);
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

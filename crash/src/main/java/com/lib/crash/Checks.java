package com.lib.crash;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * 报错异常之后。
 * 这个可以用来防止弹出的窗体会出现多个的问题
 * @author zhongjh
 */
class Checks {

    private static final String PROCESS = ":woodpecker";

    static boolean isWoodpeckerRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(
            Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infoList = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infoList) {
            Log.e("~~~processName", info.processName);
            if (info.processName.endsWith(PROCESS)) {
                Log.e("~~~isWoodpeckerRunning", "true");
                return true;
            } else {
                Log.e("~~~isWoodpeckerRunning", "false");
            }
        }
        return false;
    }
}

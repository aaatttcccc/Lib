package com.lib.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by zhongjh on 2017/5/31.
 */

public class PackageManagerUtils {

    /**
     * 获取包名
     * @param context
     * @return
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        String name = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(
                    context.getApplicationInfo().packageName, 0);
            name = (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (final PackageManager.NameNotFoundException e) {
            String[] packages = context.getPackageName().split(".");
            name = packages[packages.length - 1];
        }
        return name;
    }

    /**
     * 收集设备参数信息
     *
     * @param context 上下文
     */
    public static ArrayList<String> collectDeviceInfo(Context context) {
        ArrayList<String> message = new ArrayList<>();
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null" :
                        pi.versionName;
                String versionCode = pi.versionCode + "";
                //app版本号
                message.add("versionName：" + versionName);
                message.add("versionCode：" + versionCode);
                message.add("MODEL：" + Build.MODEL);//获取手机的型号 设备名称
                message.add("SDK_INT：" + Build.VERSION.SDK_INT);//系统的API级别 数字表示
                message.add("PRODUCT：" + Build.PRODUCT);//整个产品的名称
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        //手机各种信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                message.add(field.getName() + "：" + field.get(null).toString());
            } catch (Exception ignored) {
            }
        }
        return message;
    }

}

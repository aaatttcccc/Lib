package com.lib.library.utils.displaymetrics;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import com.lib.library.utils.constants.ScreenConstants;
import com.lib.library.utils.storage.PreferenceUtils;

/**
 * 获取手机分辨率的宽高，先从缓存获取，如果缓存都没有，则重新计算，并且保存
 * Created by zhongjh on 2017/10/25.
 */
public class DisplayMetricsSPUtils {

    /**
     * 获取屏幕分辨率-高
     *
     * @param context 上下文
     * @return 高
     */
    public static int getScreenHeight(Context context) {
        if (PreferenceUtils.getInstance(context).getIntParam(ScreenConstants.ScreenHeight, 0) == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            int screenWidth = size.x;
            int screenHeight = size.y;
            PreferenceUtils.getInstance(context).saveParam(ScreenConstants.ScreenHeight, screenHeight);
            PreferenceUtils.getInstance(context).saveParam(ScreenConstants.ScreenWidth, screenWidth);
        }
        return PreferenceUtils.getInstance(context).getIntParam(ScreenConstants.ScreenHeight, 0);
    }

    /**
     * 获取屏幕分辨率- 宽
     *
     * @param context 上下文
     * @return 宽
     */
    public static int getScreenWidth(Context context) {
        if (PreferenceUtils.getInstance(context).getIntParam(ScreenConstants.ScreenWidth, 0) == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            int screenWidth = size.x;
            int screenHeight = size.y;
            PreferenceUtils.getInstance(context).saveParam(ScreenConstants.ScreenHeight, screenHeight);
            PreferenceUtils.getInstance(context).saveParam(ScreenConstants.ScreenWidth, screenWidth);
        }
        return PreferenceUtils.getInstance(context).getIntParam(ScreenConstants.ScreenWidth, 0);
    }

}

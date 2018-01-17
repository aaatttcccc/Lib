package com.lib.library.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;

/**
 * 资源的工具类
 * Created by zhongjh on 2017/7/9 0009.
 */
public class AttrUtils {

    /**
     * 获取系统默认颜色
     *
     * @param context 上下文
     * @param resid   目前所知有以下4种颜色：R.attr.colorPrimary R.attr.colorPrimaryDark R.attr.colorAccent R.attr.colorControlActivated
     * @return 返回默认颜色
     */
    public static int getAttrColor(Context context, int resid) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(resid, typedValue, true);
        return typedValue.data;
    }

    /**
     * 兼容多个版本的赋值颜色
     *
     * @param view     要赋值颜色的view
     * @param context  上下文
     * @param activity 窗体
     * @param color    颜色
     */
    public static void setBackgroundColor(View view, Context context, Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(ContextCompat.getDrawable(context, color));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setBackgroundColor(activity.getResources().getColor(color, activity.getTheme()));
            } else {
                view.setBackgroundColor(activity.getResources().getColor(color));
            }
        }
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

}

package com.lib.library.utils.view;

import android.support.design.widget.TabLayout;
import android.widget.LinearLayout;

/**
 * TabLayout控件
 */
public class TabLayoutUtils {

    /**
     * 设置启用/禁用点击
     *
     * @param tabLayout
     */
    public static void setEnable(TabLayout tabLayout, boolean isEnable) {
        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
        for (int k = 0; k < tabStrip.getChildCount(); k++) {
            // 因为所有触摸事件，返回false都是继续下一个的，所以跟enable是相反的
            tabStrip.getChildAt(k).setOnTouchListener((v, event) -> !isEnable);
        }
    }

}

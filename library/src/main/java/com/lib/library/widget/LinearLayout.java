package com.lib.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 自定LinearLayout,起初是为了解决setOnTouchListener
 * Created by Administrator on 2017\11\27 0027.
 */
public class LinearLayout extends android.widget.LinearLayout {

    public LinearLayout(Context context) {
        super(context);
    }

    public LinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 解决google提示
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean performClick() {
        return true;
    }

}

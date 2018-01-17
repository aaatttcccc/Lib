package com.lib.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

/**
 * 自定EditText,起初是为了解决setOnTouchListener
 * Created by Administrator on 2017\11\27 0027.
 */
public class EditText extends android.support.v7.widget.AppCompatEditText {


    public EditText(Context context) {
        super(context);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditText(Context context, AttributeSet attrs, int defStyleAttr) {
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

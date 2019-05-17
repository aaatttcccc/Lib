package com.lib.library.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class InputUtils {


    /**
     * 显示输入法
     *
     * @param editText 输入法控件
     */
    public static void showSoftInput(EditText editText) {
        editText.requestFocus();
        //弹出软键盘
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏输入法
     */
    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanger != null && inputmanger.isActive()) {
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


}

package com.lib.library.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;


import com.lib.library.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 封装的一个dialog
 * Created by zhongjh
 * on 2017 2017/4/12
 * SweetAlertDialog.ERROR_TYPE:X图标
 * SweetAlertDialog.SUCCESS_TYPE:√图标
 * SweetAlertDialog.WARNING_TYPE:!图标
 *
 * @author zhongjh
 */
public class DialogHelper {
    /**
     * X图标
     */
    public final static int ERROR_TYPE = SweetAlertDialog.ERROR_TYPE;
    /**
     * √图标
     */
    public final static int SUCCESS_TYPE = SweetAlertDialog.SUCCESS_TYPE;
    /**
     * !图标
     */
    public final static int WARNING_TYPE = SweetAlertDialog.WARNING_TYPE;

    /**
     * 创建提示内容
     *
     * @param context     上下文
     * @param message     信息内容
     * @param isAutoClose 是否自动关闭
     * @param iconType    ERROR_TYPE,SUCCESS_TYPE,WARNING_TYPE 3种类型
     */
    public static void showSimpleDialog(Context context, CharSequence message, boolean isAutoClose, int iconType) {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, iconType);
        sweetAlertDialog.setTitleText(context.getResources().getString(R.string.msg_alter_title));
        sweetAlertDialog.setContentText(message.toString());
        sweetAlertDialog.show();
        sweetAlertDialog.setCancelable(true);//设置点击外部取消
        if (isAutoClose) {
            new Handler().postDelayed(() -> {
                if (sweetAlertDialog.isShowing()) {
                    sweetAlertDialog.dismiss();
                }
            }, 1000);
        }
        sweetAlertDialog.show();
    }

    /**
     * 创建进行的对话框
     */
    public static SweetAlertDialog showSimpleProgressDialog(Context context) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(context.getString(R.string.msg_loading));
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

    public static void dismissSimpleProgressDialog(SweetAlertDialog dialog) {
        dialog.dismiss();
    }

}

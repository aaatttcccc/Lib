package com.lib.library.utils.dialog;

import android.app.Activity;
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
     * @param activity    窗体
     * @param message     信息内容
     * @param isAutoClose 是否自动关闭
     * @param iconType    ERROR_TYPE,SUCCESS_TYPE,WARNING_TYPE 3种类型
     */
    public static void showSimpleDialog(Activity activity, CharSequence message, boolean isAutoClose, int iconType) {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, iconType);
        sweetAlertDialog.setTitleText(activity.getResources().getString(R.string.msg_alter_title));
        sweetAlertDialog.setConfirmText("确定");
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
     * 创建提示内容
     *
     * @param activity               窗体
     * @param message                信息内容
     * @param isAutoClose            是否自动关闭
     * @param iconType               ERROR_TYPE,SUCCESS_TYPE,WARNING_TYPE 3种类型
     * @param onConfirmClickListener 确定事件
     * @param onCancelClickListener  取消事件
     */
    public static void showSimpleDialog(Activity activity, CharSequence message, boolean isAutoClose, int iconType,
                                        SweetAlertDialog.OnSweetClickListener onConfirmClickListener, SweetAlertDialog.OnSweetClickListener onCancelClickListener) {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, iconType);
        sweetAlertDialog.setTitleText(activity.getResources().getString(R.string.msg_alter_title));
        sweetAlertDialog.setConfirmText("确定");
        sweetAlertDialog.setContentText(message.toString());
        sweetAlertDialog.show();
        sweetAlertDialog.setCancelable(true);//设置点击外部取消
        sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {
            onConfirmClickListener.onClick(sweetAlertDialog1);
            sweetAlertDialog1.dismiss();
        });
        if (onCancelClickListener != null) {
            sweetAlertDialog.setCancelText("取消");
            sweetAlertDialog.setCancelClickListener(sweetAlertDialog1 -> {
                onCancelClickListener.onClick(sweetAlertDialog1);
                sweetAlertDialog1.dismiss();
            });
        }
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

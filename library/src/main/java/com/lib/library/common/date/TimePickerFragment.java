package com.lib.library.common.date;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

/**
 * 时间dialog
 * Created by zhongjh on 2017/5/4.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public final static String TAGTIME = "tagtime";

    public final static String DATE = "date";
    String mDate = "";

    private String mTime = "";
    private ComeBack comeBack;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        //获取传递过来的日期
        if (args != null) {
            mDate = args.getString(DATE, "");
        }


        //新建日历类用于获取当前时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //返回TimePickerDialog对象
        //因为实现了OnTimeSetListener接口，所以第二个参数直接传入this
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    //实现OnTimeSetListener的onTimeSet方法
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (mDate.equals("")) {
            mTime = hourOfDay + ":" + minute;
        } else {
            mTime = mDate + " " + hourOfDay + ":" + minute;
        }
        //调用activity的getData方法将数据传回activity显示
        if (getComeBack() != null) {
            getComeBack().getData(mTime, this.getTag());
        }
    }

    public ComeBack getComeBack() {
        return comeBack;
    }

    public void setComeBack(ComeBack comeBack) {
        this.comeBack = comeBack;
    }


    public interface ComeBack {
        void getData(String data, String tag);
    }

}

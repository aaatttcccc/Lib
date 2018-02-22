package com.haibin.calendarview.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * 带分组浮动的RecyclerView
 * Created by haibin on 2017/5/15.
 */
@SuppressWarnings("all")
public class GroupRecyclerView extends RecyclerView {
    private int mGroupHeight;
    private int mGroutBackground, mTextColor;
    private int mTextSize;
    private int mPaddingLeft, mPaddingRight;
    private boolean isCenter;
    protected int mChildItemOffset;
    private boolean isHasHeader;
    private OnGroupChangeListener mListener;

    public GroupRecyclerView(Context context) {
        super(context);
    }

    public GroupRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnGroupChangeListener(OnGroupChangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 分组最上面改变通知
     */
    public interface OnGroupChangeListener {
        void onGroupChange(int groupPosition, String group);
    }
}

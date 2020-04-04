package com.lib.library.phone;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.lib.library.phone.base.MySupportActivity;
import com.lib.library.utils.InputUtils;


public abstract class BaseActivity extends MySupportActivity {

    private String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        onInitCreate(savedInstanceState);
        onInitListener();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onAfterSetContentView();
    }

    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onAfterSetContentView();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        onAfterSetContentView();
    }

    protected void onAfterSetContentView() {

    }

    @Override
    protected void onResume() {
        InputUtils.hideSoftInput(getActivity());
        super.onResume();
    }

    @Override
    protected void onPause() {
        InputUtils.hideSoftInput(getActivity());
        super.onPause();
    }

    /**
     * 返回自己
     */
    public final BaseActivity getActivity() {
        return this;
    }


    /**
     * 等同于onCreate之后(Bundle savedInstanceState)
     *
     * @param savedInstanceState bundle
     * @see #onCreate
     */
    protected abstract void onInitCreate(Bundle savedInstanceState);

    /**
     * 初始化Listener 事件
     */
    protected abstract void onInitListener();



}

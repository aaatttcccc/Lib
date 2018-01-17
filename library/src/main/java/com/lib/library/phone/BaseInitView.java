package com.lib.library.phone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 这是规划了View的初始方法
 * Created by zhongjh on 2017/4/30.
 */
public abstract class BaseInitView {

    public View view;
    public Bundle savedInstanceState;
    public LayoutInflater inflater;
    public ViewGroup viewGroup;

    public BaseInitView() {
        init();
    }

    public BaseInitView(View view) {
        this.view = view;
        init();
    }

    public BaseInitView(View view, Bundle savedInstanceState) {
        this.view = view;
        this.savedInstanceState = savedInstanceState;
        init();
    }

    public BaseInitView(View view, LayoutInflater inflater, ViewGroup viewGroup) {
        this.view = view;
        this.inflater = inflater;
        this.viewGroup = viewGroup;
        init();

    }

    private void init() {
        ButterKnife.bind(this, view);
        initUI();
        initListener();
    }


    protected void initialView() {
        initUI();
        initListener();
    }

    protected abstract void initUI();

    protected abstract void initListener();


}

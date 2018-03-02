package com.lib.library.phone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.lib.library.phone.base.MySupportActivity;
import com.lib.library.utils.InputUtils;

import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int indext = 0; indext < fragmentManager.getFragments().size(); indext++) {
            Fragment fragment = fragmentManager.getFragments().get(indext); //找到第一层Fragment
            if (fragment == null)
                Log.w(TAG, "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            else
                handleResult(fragment, requestCode, resultCode, data);
        }
    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag        递归的fragment
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data        数据源
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
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

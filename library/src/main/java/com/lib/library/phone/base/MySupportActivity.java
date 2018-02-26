package com.lib.library.phone.base;

import android.content.Intent;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 展示自定制的MySupportActivity，不继承SupportActivity
 * Created by YoKey on 17/6/24.
 */
public class MySupportActivity extends SupportActivity {

    @Override
    public void finish() {
        MySupportFragment fragment = (MySupportFragment) getTopFragment();
        if (fragment != null) {
            fragment.finish();
        }
        super.finish();
    }

    @Override
    public void finishAfterTransition() {
        MySupportFragment fragment = (MySupportFragment) getTopFragment();
        if (fragment != null) {
            fragment.finishAfterTransition();
        }
        super.finishAfterTransition();
    }

    /**
     * 转换数据变换之后
     * @param data 数据源
     */
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        MySupportFragment fragment = (MySupportFragment) getTopFragment();
        if (fragment != null) {
            fragment.onActivityReenter(resultCode,data);
        }
    }



}

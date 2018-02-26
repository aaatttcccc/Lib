package com.lib.library.phone.base;

import android.content.Intent;
import android.os.Bundle;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 展示自定制的MySupportFragment，不继承SupportFragment
 * Created by YoKey on 17/6/24.
 */
public class MySupportFragment extends SupportFragment {

    public Bundle mReenterState; // 这是获取返回来的界面的共享元素数据
    public Bundle mBundleResult; // 返回bundle的数据

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        mBundleResult = bundle;
        super.setFragmentResult(resultCode, bundle);
    }

    /**
     * 转换数据变换之后
     *
     * @param data 数据源
     */
    public void onActivityReenter(int resultCode, Intent data) {
        mReenterState = new Bundle(data.getExtras());
    }

    /**
     * activity关闭之后触发
     */
    public void finish() {
    }

    public void finishAfterTransition() {
    }
}

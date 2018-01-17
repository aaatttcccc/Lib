package com.lib.library.utils.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentationHack;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.internal.ResultRecord;
import me.yokeyword.fragmentation.helper.internal.TransactionRecord;

import static me.yokeyword.fragmentation.TransactionDelegate.FRAGMENTATION_ARG_IS_SHARED_ELEMENT;
import static me.yokeyword.fragmentation.TransactionDelegate.FRAGMENTATION_ARG_RESULT_RECORD;

/**
 * 这是基于Fragmentation框架的工具，因为google官方库add的原因
 * Created by Administrator on 2017\11\14 0014.
 */
public class FragmentationUtils {

    private static final String FRAGMENTATION_ARG_CONTAINER = "fragmentation_arg_container";

    /**
     * 替换fragment
     *
     * @param fromFragment      发出请求的fragment
     * @param toFragment        新打开的fragment
     * @param requestCode       请求值
     * @param sharedElementList 共享动画列表
     */
    public static void replace(SupportFragment fromFragment, SupportFragment toFragment, int requestCode, ArrayList<TransactionRecord.SharedElement> sharedElementList) {
        FragmentManager fragmentManager = fromFragment.getFragmentManager();
        Bundle args = getArguments(toFragment);
        args.putInt(FRAGMENTATION_ARG_CONTAINER, fromFragment.getSupportDelegate().mContainerId);
        String toFragmentTag = toFragment.getClass().getName();
        FragmentationHack.reorderIndices(fragmentManager);
        Bundle bundle = getArguments(toFragment);
        ResultRecord resultRecord = new ResultRecord();
        resultRecord.requestCode = requestCode;
        bundle.putParcelable(FRAGMENTATION_ARG_RESULT_RECORD, resultRecord);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        args = getArguments(toFragment);
        args.putBoolean(FRAGMENTATION_ARG_IS_SHARED_ELEMENT, true);
        if (sharedElementList != null)
            for (TransactionRecord.SharedElement item : sharedElementList) {
                ft.addSharedElement(item.sharedElement, item.sharedName);
            }
        ft.replace(fromFragment.getSupportDelegate().mContainerId, toFragment);
//        ft.add(fromFragment.getSupportDelegate().mContainerId, toFragment, toFragmentTag);
//        ft.hide(fromFragment);

        ft.addToBackStack(toFragmentTag);
        ft.commitAllowingStateLoss();
    }

    private static Bundle getArguments(Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            fragment.setArguments(bundle);
        }
        return bundle;
    }

}

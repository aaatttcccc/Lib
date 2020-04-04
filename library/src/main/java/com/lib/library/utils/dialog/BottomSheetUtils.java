package com.lib.library.utils.dialog;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.lib.library.utils.InputUtils;
import com.lib.library.utils.displaymetrics.DisplayMetricsUtils;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.List;

/**
 * 动态底部框的封装控件
 * Created by Administrator on 2017\10\26 0026.
 */
public class BottomSheetUtils {

    public SweetSheet sweetSheet;
    private SweetSheet.OnMenuItemClickListener mOnMenuItemClickListener;


    /**
     * 创建并且用于显示
     *
     * @param activity       用于隐藏输入法的
     * @param relativeLayout 父布局
     * @param menuRes        数据源
     */
    public BottomSheetUtils(Activity activity, RelativeLayout relativeLayout, int menuRes) {
        init(activity, relativeLayout, null, menuRes);
    }

    /**
     * 创建并且用于显示
     *
     * @param activity    用于隐藏输入法的
     * @param frameLayout 父布局
     * @param menuRes     数据源
     */
    public BottomSheetUtils(Activity activity, FrameLayout frameLayout, int menuRes) {
        init(activity, frameLayout, null, menuRes);
    }

    /**
     * 创建并且用于显示
     *
     * @param activity     用于隐藏输入法的
     * @param frameLayout  父布局
     * @param menuEntities 数据源
     */
    public BottomSheetUtils(Activity activity, FrameLayout frameLayout, List<MenuEntity> menuEntities) {
        init(activity, frameLayout, menuEntities, -1);
    }

    /**
     * 创建并且用于显示
     *
     * @param activity       用于隐藏输入法的
     * @param relativeLayout 父布局
     * @param menuEntities   数据源
     */
    public BottomSheetUtils(Activity activity, RelativeLayout relativeLayout, List<MenuEntity> menuEntities) {
        init(activity, relativeLayout, menuEntities, -1);
    }

    /**
     * 创建并且用于显示
     */
    private void init(Activity activity, ViewGroup viewGroup, List<MenuEntity> menuEntities, int menuRes) {
        if (sweetSheet == null) {
            sweetSheet = new SweetSheet(viewGroup);
            if (menuEntities != null) {
                sweetSheet.setMenuList(menuEntities);
            } else {
                sweetSheet.setMenuList(menuRes);
            }
            RecyclerViewDelegate recyclerViewDelegate = new RecyclerViewDelegate(true);
            //数据源如果小于3行，则根据数据来设置固定高度
            if (sweetSheet.getmMenuEntities().size() == 1)
                recyclerViewDelegate.setContentHeight(DisplayMetricsUtils.dip2px(62));// 设置高度
            if (sweetSheet.getmMenuEntities().size() == 2)
                recyclerViewDelegate.setContentHeight(DisplayMetricsUtils.dip2px(125));// 设置高度
            if (sweetSheet.getmMenuEntities().size() == 3)
                recyclerViewDelegate.setContentHeight(DisplayMetricsUtils.dip2px(188));// 设置高度
            if (sweetSheet.getmMenuEntities().size() == 4)
                recyclerViewDelegate.setContentHeight(DisplayMetricsUtils.dip2px(251));// 设置高度
            if (sweetSheet.getmMenuEntities().size() == 5)
                recyclerViewDelegate.setContentHeight(DisplayMetricsUtils.dip2px(314));// 设置高度
            sweetSheet.setDelegate(recyclerViewDelegate);
            sweetSheet.setBackgroundEffect(new DimEffect(4));
            InputUtils.hideSoftInput(activity);

            sweetSheet.setOnMenuItemClickListener((position, menuEntity) -> {
                sweetSheet.dismiss();
                mOnMenuItemClickListener.onItemClick(position, menuEntity);
                return false;
            });

        }
    }

    /**
     * 点击事件
     *
     * @param onMenuItemClickListener 事件监听
     */
    public void setOnMenuItemClickListener(SweetSheet.OnMenuItemClickListener onMenuItemClickListener) {
        mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /**
     * 是否显示
     *
     * @return
     */
    public boolean isShow() {
        if (sweetSheet != null)
            return sweetSheet.isShow();
        return false;
    }

    /**
     * 打开
     */
    public void show() {
        if (sweetSheet != null)
            sweetSheet.show();
    }

    /**
     * 关闭
     */
    public void dismiss() {
        if (sweetSheet != null) {
            if (sweetSheet.isShow()) {
                sweetSheet.dismiss();
            }
        }
    }

}

package com.lib.library.widget.coordinatorLayout;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * 滑动内容的时候，控制其他控件显示隐藏的
 * Created by Administrator on 2017\11\13 0013.
 */
public class ContentBehavior extends CoordinatorLayout.Behavior {


    public ContentBehavior() {
    }

    public ContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean visible = true;//是否可见

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //被观察者发生滑动的开始的时候回调的
        //nestedScrollAxes:滑动关联轴，现在只关心垂直的滑动。
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //被观察者滑动的时候回调的
        if (dyConsumed > 0 && visible) {
            //show
            visible = false;
            onHide(child);
        } else if (dyConsumed < 0) {
            //hide
            visible = true;
            onShow(child);
        }
    }

    private void onHide(View view) {
        // 隐藏动画--属性动画
        if (view instanceof Toolbar){
            ViewCompat.animate(view).translationY(-(view.getHeight() * 2)).setInterpolator(new AccelerateInterpolator(3));
        }else if (view instanceof FloatingActionButton){
            ViewCompat.animate(view).translationY(view.getHeight() * 2).setInterpolator(new AccelerateInterpolator(3));
        }else{

        }
    }

    private void onShow(View view) {
        // 显示动画--属性动画
        ViewCompat.animate(view).translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }



}

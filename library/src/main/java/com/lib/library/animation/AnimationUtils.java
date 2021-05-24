package com.lib.library.animation;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.lib.library.R;

/**
 * 动画集合工具
 * Created by Administrator on 2017/7/27 0027.
 */
public class AnimationUtils {
    // 动画形式
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    /**
     * 回调接口
     */
    public interface OnVectorAnimation {

        /**
         * 初始动画
         */
        void onAnimationStart();

        /**
         * 结束动画
         */
        void onAnimationEnd();
    }

    /**
     * 兼容VectorAnimation
     *
     * @param animatedVectorDrawableCompat 该控件
     * @param onVectorAnimation         结束动画事件
     */
    public static void VectorAnimationCallback(AnimatedVectorDrawableCompat animatedVectorDrawableCompat, OnVectorAnimation onVectorAnimation) {
        animatedVectorDrawableCompat.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                onVectorAnimation.onAnimationStart();
                super.onAnimationStart(drawable);
            }

            @Override
            public void onAnimationEnd(Drawable drawable) {
                onVectorAnimation.onAnimationEnd();
                super.onAnimationEnd(drawable);
            }
        });
    }


    /**
     * View渐现动画效果
     *
     * @param alphaAnimation 动画的本身，防止重复
     * @param view           view
     * @param duration       间隔时间
     */
    public static AlphaAnimation showFade(AlphaAnimation alphaAnimation, View view, int duration) {
        if (null == view || duration < 0) {
            return null;
        }
        view.setVisibility(View.GONE);
        if (null != alphaAnimation) {
            alphaAnimation.cancel();
        }
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return alphaAnimation;
    }

    /**
     * 旋转动画
     *
     * @param activity 上下文
     * @param view    控件
     */
    public static void rotate(Activity activity, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.rotate);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 下降动画
     *
     * @param floatingActionButton floatingActionButton
     */
    public static void DropAnimation(FloatingActionButton floatingActionButton) {
        ViewCompat.animate(floatingActionButton).translationY(floatingActionButton.getHeight() + getMarginBottom(floatingActionButton)).setInterpolator(INTERPOLATOR).withLayer().start();
    }

    /**
     * 上升动画
     *
     * @param floatingActionButton floatingActionButton
     */
    public static void RisingAnimation(FloatingActionButton floatingActionButton) {
        ViewCompat.animate(floatingActionButton).translationY(0)
                .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                .start();
    }

    /**
     * 获取底部距离值
     *
     * @param v 按钮
     * @return 数值
     */
    private static int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }


}

package com.lib.library.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.lib.library.R;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * 圆形扩散的动画
 * Created by Administrator on 2017\11\25 0025.
 * https://medium.com/@gabornovak/circular-reveal-animation-between-fragments-d8ed9011aec
 */
public class CircularRevealAnimationUtils {

    public interface AnimationFinishedListener {
        void onAnimationFinished();
    }

    public static int getMediumDuration(Context context) {
        int duration;
        if (true) {
            duration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        } else {
            duration = 0;
        }
        return duration;
    }

    /**
     * 圆形扩散的动画
     *
     * @param context        上下文
     * @param view           母窗体，基于该窗体出现的时候才执行这些动画
     * @param revealSettings 所需要的参数
     * @param startColor     渐变颜色的开端
     * @param endColor       渐变颜色的最终扩散
     */
    public static void registerCircularRevealAnimation(final Context context, final View view, final RevealAnimationSetting revealSettings, final int startColor, final int endColor, final AnimationFinishedListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);

                    int cx = revealSettings.getCenterX();
                    int cy = revealSettings.getCenterY();
                    int width = revealSettings.getWidth();
                    int height = revealSettings.getHeight();

                    //Simply use the diagonal of the view
                    float finalRadius = (float) Math.sqrt(width * width + height * height);
                    Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
                    anim.setDuration(getMediumDuration(context));
                    anim.setInterpolator(new FastOutSlowInInterpolator());
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            listener.onAnimationFinished();
                        }
                    });
                    anim.start();
                    startBackgroundColorAnimation(view, startColor, endColor, getMediumDuration(context));
                }
            });
        } else {
            listener.onAnimationFinished();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void startCircularRevealExitAnimation(Context context, final View view, RevealAnimationSetting revealSettings, int startColor, int endColor, final AnimationFinishedListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = revealSettings.getCenterX();
            int cy = revealSettings.getCenterY();
            int width = revealSettings.getWidth();
            int height = revealSettings.getHeight();

            float initRadius = (float) Math.sqrt(width * width + height * height);
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initRadius, 0);
            anim.setDuration(getMediumDuration(context));
            anim.setInterpolator(new FastOutSlowInInterpolator());
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //Important: This will prevent the view's flashing (visible between the finished animation and the Fragment remove)
                    view.setVisibility(View.GONE);
                    listener.onAnimationFinished();
                }
            });
            anim.start();
            startBackgroundColorAnimation(view, startColor, endColor, getMediumDuration(context));
        } else {
            listener.onAnimationFinished();
        }
    }

    private static void startBackgroundColorAnimation(final View view, int startColor, int endColor, int duration) {
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(startColor, endColor);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setDuration(duration);
        anim.addUpdateListener(valueAnimator -> view.setBackgroundColor((Integer) valueAnimator.getAnimatedValue()));
        anim.start();
    }


    /**
     * 关闭事件
     */
    public interface Dismissible {
        interface OnDismissedListener {
            void onDismissed();
        }

        void dismiss(OnDismissedListener listener);
    }

    /**
     * 传递的实体类
     */
    public static class RevealAnimationSetting implements Parcelable {

        private int centerX;

        private int centerY;

        private int width;

        private int height;

        public int getCenterX() {
            return centerX;
        }

        public void setCenterX(int centerX) {
            this.centerX = centerX;
        }

        public int getCenterY() {
            return centerY;
        }

        public void setCenterY(int centerY) {
            this.centerY = centerY;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.centerX);
            dest.writeInt(this.centerY);
            dest.writeInt(this.width);
            dest.writeInt(this.height);
        }

        public RevealAnimationSetting() {
        }

        protected RevealAnimationSetting(Parcel in) {
            this.centerX = in.readInt();
            this.centerY = in.readInt();
            this.width = in.readInt();
            this.height = in.readInt();
        }

        public final Creator<RevealAnimationSetting> CREATOR = new Creator<RevealAnimationSetting>() {
            @Override
            public RevealAnimationSetting createFromParcel(Parcel source) {
                return new RevealAnimationSetting(source);
            }

            @Override
            public RevealAnimationSetting[] newArray(int size) {
                return new RevealAnimationSetting[size];
            }
        };
    }

}
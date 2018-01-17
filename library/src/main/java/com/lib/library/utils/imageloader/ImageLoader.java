package com.lib.library.utils.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lib.library.R;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

/**
 * 异步加载图片
 * crossFade:添加图片淡入加载的效果
 * CenterCrop:会缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分。ImageVIew会被完全填充满，但是图片可能不能完全显示出
 * 教程:http://www.jianshu.com/p/e78407a18716
 * SimpleTarget 获取bitmap
 */
public class ImageLoader {

    private static RequestOptions myOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_error_outline_black_24dp)
            .error(R.drawable.ic_error_outline_black_24dp)
            .priority(Priority.HIGH);


//    /**
//     * 根据本地图片路径加载图片
//     *
//     * @param fragment  当前fragment
//     * @param imageUrl  图片url
//     */
//    public static void with(final Fragment fragment, String imageUrl) {
//        Glide.with(fragment)
//                .load(imageUrl)
//                .apply(myOptions)
//                .crossFade()
//                .into(glideDrawableImageViewTarget);
////                .into(new GlideDrawableImageViewTarget(imageView) {
////                    public void onResourceReady(GlideDrawable drawable, GlideAnimation<? super GlideDrawable> anim) {
////                        super.onResourceReady(drawable, anim);
////                    }
////                });
//    }

    /**
     * 根据本地图片路径加载图片 (已知图片大小)
     *
     * @param fragment
     * @param imageUrl
     * @param imageView
     * @param width
     * @param height
     */
    public static void with(final Fragment fragment, String imageUrl, final ImageView imageView, int width, int height) {
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .override(width, height)
                .placeholder(R.drawable.ic_error_outline_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .priority(Priority.HIGH);
        Glide.with(fragment)
                .load(imageUrl)
                .apply(myOptions)
                .into(imageView);
    }

    /**
     * 根据本地图片路径加载图片 (圆图)
     *
     * @param fragment  当前fragment
     * @param imageUrl  图片url
     * @param imageView 图片控件
     */
    public static void withCircular(Fragment fragment, String imageUrl, ImageView imageView) {
        Glide.with(fragment)
                .asBitmap()
                .load(imageUrl)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(fragment.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 根据资源加载图片
     *
     * @param fragment  当前fragment
     * @param imageUrl  图片url
     * @param imageView 图片控件
     */
    public static void with(Fragment fragment, int imageUrl, ImageView imageView) {
        Glide.with(fragment)
                .load(imageUrl)
                .into(imageView);
    }

    /**
     * 根据资源加载图片
     *
     * @param activity  当前activity
     * @param imageUrl  图片url
     * @param imageView 图片控件
     */
    public static void with(Activity activity, int imageUrl, ImageView imageView) {
        Glide.with(activity)
                .load(imageUrl)
                .into(imageView);
    }

    /**
     * 根据资源加载图片
     *
     * @param context  当前上下文
     * @param imageUrl  图片url
     * @param imageView 图片控件
     */
    public static void with(Context context, int imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }


    /**
     * 根据网页加载图片
     *
     * @param fragment  当前fragment
     * @param imageUrl  图片url
     * @param imageView 图片控件
     */
    public static void with(Fragment fragment, String imageUrl, ImageView imageView) {
        Glide.with(fragment)
                .load(imageUrl)
                .into(imageView);
    }

    /**
     * 根据网页加载图片
     *
     * @param activity  当前activity
     * @param imageUrl  图片url
     * @param imageView 图片控件
     */
    public static void with(Activity activity, String imageUrl, ImageView imageView) {
        Glide.with(activity)
                .load(imageUrl)
                .into(imageView);
    }

}

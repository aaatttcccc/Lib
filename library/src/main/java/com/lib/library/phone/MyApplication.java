package com.lib.library.phone;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

public class MyApplication extends Application {

    private BaseActivity mCurrentActivity;
    private static MyApplication mInstance = null;

    public static MyApplication getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Application is not created.");
        }
        return mInstance;
    }


    protected Boolean networkAvailable = false;


    private Toast mToast;

    /**
     * 用于显示同一个toast
     */
    public Toast showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        }
        mToast.setText(message);
        mToast.show();
        return mToast;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initRegisterActivityLifecycleCallbacks(); //回调函数
        initWindow();//初始化屏幕信息并且保存进缓存
        onPreCreateApplication();

    }

    /**
     * 初始化屏幕信息并且保存进缓存
     */
    private void initWindow() {
    }

    /**
     * 回调函数,Activity的回调
     */
    private void initRegisterActivityLifecycleCallbacks() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                try {
                    mCurrentActivity = (BaseActivity) activity;
                } catch (Exception ex) {
                    //抛出异常是为了一些第三方本身是Activity
                }
            }
        });
    }

    /**
     * 让子类可以调用的
     */
    protected void onPreCreateApplication() {
    }


}

package com.lib.library.rxjava;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.widget.Toast;

import com.lib.library.event.MyApplicationEvent;
import com.lib.library.log.PrintToFileLogger;

import org.greenrobot.eventbus.EventBus;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 封装
 * Created by zhongjh on 2017/5/22.
 */
public abstract class RxObserver<T> implements Observer<T> {

    private CompositeDisposable mCompositeDisposable;

    public RxObserver(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onNext(T value) {
        onRxNext(value);
    }

    @Override
    public void onError(Throwable e) {
        EventBus.getDefault().post(new MyApplicationEvent(e));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onRxNext(T t);

}

package com.lib.library.rxjava;

import com.lib.library.event.MyApplicationEvent;

import org.greenrobot.eventbus.EventBus;

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

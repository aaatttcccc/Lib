package com.lib.library.utils.rxjava2;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 观察者装饰器
 */
public class ObservableDecorator {

    public static <T> Observable<T> decorate(Observable<T> observable) {
        Observable<T> newObservable;
        if (BoreConstants.isUnitTest) {
            newObservable = observable.subscribeOn(Schedulers.trampoline())
                    .observeOn(Schedulers.trampoline());
        } else {
            newObservable = observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
//                    .delay(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()); // 模拟延迟,用于观察加载框等效果
        }
        return newObservable;
    }
}

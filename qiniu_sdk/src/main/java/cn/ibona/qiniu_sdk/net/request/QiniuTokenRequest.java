package cn.ibona.qiniu_sdk.net.request;

import com.lib.library.rxjava.ApiDefault;

import cn.ibona.qiniu_sdk.net.bean.QiNiuToken;
import cn.ibona.qiniu_sdk.net.listener.Api;
import cn.ibona.qiniu_sdk.net.listener.TokenLisntener;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static cn.ibona.qiniu_sdk.net.NetUrl.TOKEN_URL;

/**
 * 获取Token请求
 */
public class QiniuTokenRequest {

    /**
     * token 获取监听
     */
    private TokenLisntener tokenLisntener;

    public void setTokenLisntener(TokenLisntener tokenLisntener) {
        this.tokenLisntener = tokenLisntener;
    }

    /**
     * 获取token
     *
     */
    public static void getToken( final TokenLisntener tokenLisntener) {

        final QiniuTokenRequest tokenRequest = new QiniuTokenRequest();
        tokenRequest.setTokenLisntener(tokenLisntener);

        ApiDefault.getInstance().retrofit.create(Api.class).getCode(TOKEN_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QiNiuToken>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QiNiuToken value) {
                        if (value.getCode() == 200) {
                            tokenLisntener.getTokenSuccess(value.getData());
                        }else{
                            tokenLisntener.getTokenError(value.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        tokenLisntener.getTokenError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

package cn.ibona.qiniu_sdk.net.listener;

import cn.ibona.qiniu_sdk.net.bean.QiNiuToken;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 获取token接口
 */
public interface Api {

    @GET()
    Observable<QiNiuToken> getCode(@Url String url);

}

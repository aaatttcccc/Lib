package cn.ibona.qiniu_sdk.net.listener;

/**
 * 监听 token
 */
public interface TokenLisntener {


    /**
     * 成功回调
     * @param token token信息
     */
    void getTokenSuccess(String token);

    /**
     * 失败回调
     * @param msg 失败信息
     */
    void getTokenError(String msg);


}

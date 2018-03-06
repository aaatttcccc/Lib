package cn.ibona.qiniu_sdk.net;

import android.content.Context;

import java.util.Date;

import cn.ibona.qiniu_sdk.net.bean.UploadBean;
import cn.ibona.qiniu_sdk.net.request.QiniuUploadRequest;
import cn.ibona.qiniu_sdk.util.QiniuConstant;
import cn.ibona.qiniu_sdk.util.QiniuDateUtil;
import cn.ibona.qiniu_sdk.util.QiniuSharedPref;

/**
 * 图片上传七牛对外接口
 */
public class QiniuRequest {

    private static QiniuUploadRequest request = QiniuUploadRequest.newInstance();

    /**
     * 上传图片
     *
     * @param context       上下文
     * @param bean          上传参数
     * @param qiniuCallback 回调
     */
    public static void uploadImage(Context context, UploadBean bean, QiniuCallback qiniuCallback) {

        if (context == null) {
            return;
        }

        if (bean == null) {
            return;
        }
        // 初始化 sharedPref
        QiniuSharedPref.init(context);

        // 赋值回调事件
        request.setQiniuCallback(qiniuCallback);

        // 时间判定
        String dataString = QiniuSharedPref.getDate();
        Date date = QiniuDateUtil.getDateByString(dataString);
        Date date1 = new Date();
        assert date != null;
        if ((date1.getTime() - date.getTime()) / 1000 > 1200) {
            // 判断缓存的时间，如果超时了，就直接重新获取token并且上传。如果没超时，跳过
            request.requestToken();
            return;
        }

        // 本地判定
        String token = QiniuSharedPref.getToken();
        if (token.equals(QiniuConstant.SHARED_PREFENCE_TOKEN_DEFAULT)) {
            // 判断当前的token是不是有效的，如果没效，就直接重新获取token并且上传。如果没超时，跳过
            request.requestToken();
            return;
        }

        // 根据缓存的token上传
        bean.setToken(token);
        request.upload(bean);
    }

}

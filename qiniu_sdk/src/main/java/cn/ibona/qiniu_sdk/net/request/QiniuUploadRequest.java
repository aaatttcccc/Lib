package cn.ibona.qiniu_sdk.net.request;


import android.support.annotation.Nullable;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.io.File;

import cn.ibona.qiniu_sdk.net.QiniuCallback;
import cn.ibona.qiniu_sdk.net.bean.UploadBean;
import cn.ibona.qiniu_sdk.net.listener.TokenLisntener;
import cn.ibona.qiniu_sdk.util.QiniuConstant;
import cn.ibona.qiniu_sdk.util.QiniuDateUtil;
import cn.ibona.qiniu_sdk.util.QiniuSharedPref;

/**
 * 上传图片请求
 */
public class QiniuUploadRequest implements TokenLisntener {

    private static QiniuUploadRequest uploadRequestInstance;

    public static QiniuUploadRequest newInstance() {
        if (uploadRequestInstance == null) {
            uploadRequestInstance = new QiniuUploadRequest();
        }
        return uploadRequestInstance;
    }

    /**
     * 回调请求
     */
    private QiniuCallback qiniuCallback;

    public void setQiniuCallback(QiniuCallback qiniuCallback) {
        this.qiniuCallback = qiniuCallback;
    }


    /**
     * 上传实体类
     */
    private UploadBean uploadBean = new UploadBean();

    public void setUploadBean(UploadBean uploadBean) {
        this.uploadBean = uploadBean;
    }


    /**
     * 请求token类
     */
    private UploadManager uploadManager;

    public QiniuUploadRequest() {
        uploadManager = new UploadManager();
    }

    /**
     * 上传图片
     */
    public void upload(final UploadBean bean) {
        // 赋值实体
        this.uploadBean = bean;

        // 获取图片文件
        final File uploadFile = getFile(uploadBean.getImagePath());
        if (uploadFile == null) {
            qiniuCallback.onError("文件路径：" + QiniuConstant.UPLOAD_IMAGE_IFO);
            return;
        }

        // 进度回调
        UploadOptions uploadOptions = new UploadOptions(null, null, false,
                new UpProgressHandler() {
                    @Override
                    public void progress(String key, double percent) {
                        qiniuCallback.onProcess(percent);
                    }
                }, null);

        // 上传图片
        this.uploadManager.put(uploadFile, uploadBean.getImageName(), uploadBean.getToken(),
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo respInfo,
                                         JSONObject jsonData) {
                        if (respInfo.isOK()) {
                            qiniuCallback.onSuccess(key);
                        } else {
                            //401 , 执行回调操作
                            if (respInfo.statusCode == QiniuConstant.RESPONSE_STATUS_CODE) {
                                // 重新获取token
                                requestToken();
                            }
                        }
                    }

                }, uploadOptions);
    }

    /**
     * 请求token
     */
    public void requestToken() {
        try {
            QiniuTokenRequest.getToken(uploadRequestInstance);
        } catch (Exception e) {
            qiniuCallback.onError("请求--Token :" + e.getMessage());
        }
    }

    @Nullable
    private File getFile(String imagePath) {
        File uploadFile = new File(imagePath);
        if (!(uploadFile.exists() || uploadFile.isFile())) {
            return null;
        }
        return uploadFile;
    }


    @Override
    public void getTokenSuccess(String token) {
        // 重新请求后然后本地存储token
        QiniuSharedPref.setToken(token, QiniuDateUtil.getDateStringByNow());
        uploadBean.setToken(token);
        uploadRequestInstance.upload(uploadBean);
    }

    @Override
    public void getTokenError(String msg) {
        qiniuCallback.onError("获取token：" + msg);
    }
}

package cn.ibona.qiniu_sdk.net.bean;

/**
 * 上传信息封装
 */
public class UploadBean {

    private String imagePath;
    private String imageName;
    private String token;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UploadBean{" +
                " imagePath='" + imagePath + '\'' +
                ", imageName='" + imageName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

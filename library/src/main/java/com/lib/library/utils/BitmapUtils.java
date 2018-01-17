package com.lib.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Bitmap转换类
 * Created by Administrator on 2017/7/31 0031.
 */
public class BitmapUtils {

    /**
     * bitmap转为base64
     *
     * @param bitmap bitmap
     * @return 64位的string
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

//    String result = null;
//    if (bitmap != null) {
//        try {
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
//            byte[] b = stream.toByteArray(); // 将图片流以字符串形式存储下来
//            result = Base64Coder.encodeLines(b);//转换后的字符串，可将该字符串上传至服务器端进行解码
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    return result;


    /**
     * base64转为bitmap
     *
     * @param base64Data 64位的string
     * @return bitmap
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}

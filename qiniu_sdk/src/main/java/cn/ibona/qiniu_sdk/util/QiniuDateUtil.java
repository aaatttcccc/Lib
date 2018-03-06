package cn.ibona.qiniu_sdk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yuanmingzhuo on 16-3-10.
 * 时间工具类
 */
public class QiniuDateUtil {


    private static final SimpleDateFormat sdf = new SimpleDateFormat(QiniuConstant.QINIU_DATE_UTIL_FORMAT, Locale.CANADA);
    private static final SimpleDateFormat sdfName = new SimpleDateFormat(QiniuConstant.QINIU_DATE_UTIL_FORMAT_NAME,Locale.CANADA);

    /**
     * 2016-01-01 00:00:00 字符串 转 Date 对象
     *
     * @param date
     * @return
     */
    public static Date getDateByString(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 获取当前时间
     *
     * @return String 2016-01-01 00:00:00
     */
    public static String getDateStringByNow() {
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return String 20160101000000
     */
    public static String getDateStringByNowName() {
        return sdfName.format(new Date());
    }


}

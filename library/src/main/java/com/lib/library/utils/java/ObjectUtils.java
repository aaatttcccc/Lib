package com.lib.library.utils.java;

/**
 * @Description 对象工具类
 * @author zhongjh
 * @date 2014-10-31
 * @version V1.0
 */

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public final class ObjectUtils {

    private ObjectUtils() {

    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static String LOOG_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public final static String LOOG_MILLI_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * yyyy-MM-dd
     */
    public final static String SORT_TIME_FORMAT = "yyyy-MM-dd";

    /**
     * 0.0%
     */
    public final static DecimalFormat FORMAT_PERCENT = new DecimalFormat("0.0%");

    /**
     * ###,###
     */
    public final static DecimalFormat FORMAT_NUMBER = new DecimalFormat("###,###");

    /**
     * 判断指定的字符是否为空
     *
     * @param string
     * @return 空为true, 否则为false
     */
    public static boolean isNull(String string) {
        return string == null || "".equalsIgnoreCase(string);
    }

    /**
     * 判断指定的字符是否为空
     *
     * @param string
     * @return
     */
    public static boolean isNotNull(String string) {
        return string != null && !"".equalsIgnoreCase(string);
    }

    /**
     * 判断指定集合是否有元素
     *
     * @param collection
     * @return
     */
    public static <E> boolean isNotNull(Collection<E> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 判断指定集合是否有元素
     *
     * @param array
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean isNotNull(T... array) {
        return array != null && array.length > 0;
    }

    /**
     * 转换字符串,如果是空的，则给""默认值
     *
     * @param object
     * @return
     */
    public static String parseString(Object object) {
        return parseString(object, "");
    }

    /**
     * 转换字符串
     *
     * @param object        指定的对象
     * @param defaultValues 如果是空的，则给指定的默认值
     * @return
     */
    public static String parseString(Object object, String defaultValues) {
        return object != null ? object.toString() : defaultValues;
    }

    /**
     * 转换字符串
     *
     * @param object        指定的对象
     * @param equality      相等，比如"0"
     * @param defaultValues 如果是空的或者相等某个条件，则给指定的默认值
     * @return
     */
    public static String parseString(Object object, String equality, String defaultValues) {
        return object != null && !object.equals(equality) ? object.toString() : defaultValues;
    }

    /**
     * 转换数字,如果是空的，则给0默认值
     *
     * @param object
     * @return
     */
    public static int parseInt(Object object) {
        if (object != null && object instanceof Number) {
            return ((Number) object).intValue();
        }
        return parseInt(parseString(object));
    }

    /**
     * 转换数字,如果是空的，则给0默认值
     *
     * @param string
     * @return
     */
    public static int parseInt(String string) {
        return parseInt(string, 0);
    }

    /**
     * 转换数字
     *
     * @param string        指定的对象
     * @param defaultValues 如果是空的，则给指定的默认值
     * @return
     */
    public static int parseInt(String string, int defaultValues) {
        return isNotNull(string) ? Integer.parseInt(string) : defaultValues;
    }

    /**
     * 转换数字,如果是空的，则给0L默认值
     *
     * @param string
     * @return
     */
    public static long parseLong(Object string) {
        if (string != null && string instanceof Number) {
            return ((Number) string).longValue();
        }
        return parseLong(parseString(string));
    }

    /**
     * 转换数字,如果是空的，则给0L默认值
     *
     * @param string
     * @return
     */
    public static long parseLong(String string) {
        return parseLong(string, 0L);
    }

    /**
     * 转换数字
     *
     * @param string        指定的对象
     * @param defaultValues 如果是空的，则给指定的默认值
     * @return
     */
    public static long parseLong(String string, long defaultValues) {
        return isNotNull(string) ? Long.parseLong(string) : defaultValues;
    }

    /**
     * 转换数字,如果是空的，则给0F默认值
     *
     * @param string
     * @return
     */
    public static float parseFloat(Object string) {
        if (string != null && string instanceof Number) {
            return ((Number) string).floatValue();
        }
        return parseFloat(parseString(string), 0F);
    }

    /**
     * 转换数字,如果是空的，则给0F默认值
     *
     * @param string
     * @return
     */
    public static float parseFloat(String string) {
        return parseFloat(string, 0F);
    }

    /**
     * 转换数字
     *
     * @param string        指定的对象
     * @param defaultValues 如果是空的，则给指定的默认值
     * @return
     */
    public static float parseFloat(String string, float defaultValues) {
        return isNotNull(string) ? Float.parseFloat(string) : defaultValues;
    }

    /**
     * 转换数字,如果是空的，则给0D默认值
     *
     * @param string
     * @return
     */
    public static double parseDouble(Object string) {
        if (string != null && string instanceof Number) {
            return ((Number) string).doubleValue();
        }
        return parseDouble(parseString(string));
    }

    /**
     * 转换数字,如果是空的，则给0D默认值
     *
     * @param string
     * @return
     */
    public static double parseDouble(String string) {
        return parseDouble(string, 0D);
    }

    /**
     * 转换数字
     *
     * @param string        指定的对象
     * @param defaultValues 如果是空的，则给指定的默认值
     * @return
     */
    public static double parseDouble(String string, double defaultValues) {
        return isNotNull(string) ? Double.parseDouble(string) : defaultValues;
    }

    public static boolean parseBoolean(String string, boolean defalutValues) {
        return isNotNull(string) ? Boolean.parseBoolean(string) : defalutValues;
    }

    /**
     * 将日期转换字符串
     *
     * @param date         日期
     * @param stringFormat 日期格式
     * @return
     */
    public static String parseString(Date date, String stringFormat) {
        DateFormat format = new SimpleDateFormat(stringFormat, Locale.getDefault());
        return format.format(date);
    }

    /**
     * 将日期转换字符串,默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String parseString(Date date) {
        return parseString(date, ObjectUtils.LOOG_TIME_FORMAT);
    }

    /**
     * 将字符串转换日期
     *
     * @param stringFormat 日期格式
     * @param dateString   时间
     * @return
     */
    public static Date parseDate(String stringFormat, String dateString) {
        return parseDate(stringFormat, dateString, null);
    }

    /**
     * 将字符串转换日期
     *
     * @param stringFormat 日期格式
     * @param dateString   时间
     * @return
     */
    public static Date parseDate(String stringFormat, Object dateString) {
        return parseDate(stringFormat, parseString(dateString), null);
    }

    /**
     * 将字符串转换日期
     *
     * @param stringFormat 日期格式
     * @param dateString   时间
     * @param defaultValue 默认时间
     * @return
     */
    public static Date parseDate(String stringFormat, String dateString, Date defaultValue) {
        Date date = null;
        if (isNotNull(dateString)) {
            try {
                DateFormat format = new SimpleDateFormat(stringFormat, Locale.getDefault());
                date = format.parse(dateString);
            } catch (Exception e) {
                date = defaultValue;
            }
        }
        return date;
    }

    /**
     * 获取这个对象在这个数组里面是的第几个索引，从0开始
     *
     * @param a      数组
     * @param object 对象
     * @return
     */
    public static <T> int indexOf(T[] a, T object) {
        if (object != null) {
            for (int i = 0; i < a.length; i++) {
                if (object.equals(a[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                if (a[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 数字格式化
     *
     * @param s      :值
     * @param format :格式，例如 "#,###.00"
     * @return
     */
    public static String decimalFormat(String s, String format) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        double num = parseDouble(s);
        formater = new DecimalFormat(format);
        return formater.format(num);
    }

    /**
     * 百分比格式化
     *
     * @param object
     * @return
     */
    public static String percentInstance(Object object) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        return nf.format(object);
    }

    /**
     * 如果valueT=defaultValue,则返回空值
     *
     * @param valueT
     * @param defaultValue
     * @return
     */
    public static <T> T NullIf(T valueT, T defaultValue) {
        T a = valueT;
        if (valueT.equals(defaultValue)) {
            a = null;
        }
        return a;
    }

    /**
     * 如果object不是空的并且=defaultValue,则返回format格式处理出来object
     *
     * @param format       格式
     * @param object       对象
     * @param defaultValue 默认值
     * @return
     */
    public static <T> String formatObject(Format format, T object, T defaultValue) {
        String v = null;
        // if (object != null && !object.equals(defaultValue)) {
        // v = format.format(object);
        // }
        if (object != null) {
            v = format.format(object);
        }
        return v;
    }

    /**
     * 如果object不是空的并且=defaultValue,则返回format格式处理出来object 格式化 0.0%
     *
     * @param object       对象
     * @param defaultValue 默认值
     * @return
     */
    public static <T> String formatPercent(T object, T defaultValue) {
        return formatObject(FORMAT_PERCENT, object, defaultValue);
    }

    /**
     * 如果object不是空的并且=defaultValue,则返回format格式处理出来object 格式化###,###
     *
     * @param object       对象
     * @param defaultValue 默认值
     * @return
     */
    public static <T> String formatNumber(T object, T defaultValue) {
        return formatObject(FORMAT_NUMBER, object, defaultValue);
    }

    /**
     * 如果object不是空的并且=defaultValue,则返回format格式处理出来object
     *
     * @param object       对象
     * @param defaultValue 默认值
     * @param stringFormat 格式
     * @return
     */
    public static <T> String formatNumber(T object, T defaultValue, String stringFormat) {
        return formatObject(new DecimalFormat(stringFormat), object, defaultValue);
    }

}

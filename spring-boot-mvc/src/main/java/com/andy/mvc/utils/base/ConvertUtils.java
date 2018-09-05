package com.andy.mvc.utils.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> 转换工具类
 *
 * @author Leone
 **/
public class ConvertUtils {

    /**
     * 字符串转换为int
     *
     * @param str          待转换的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static int strToInt(String str, Integer defaultValue) {
        try {
            defaultValue = Integer.parseInt(str);
        } catch (Exception localException) {
        }
        return defaultValue;
    }

    /**
     * String转换为Long
     *
     * @param str          待转换字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Long strToLong(String str, Long defaultValue) {
        try {
            defaultValue = Long.parseLong(str);
        } catch (Exception localException) {
        }
        return defaultValue;
    }

    /**
     * 字符串转换为Float
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static Float strToFloat(String str, Float defaultValue) {
        try {
            defaultValue = Float.parseFloat(str);
        } catch (Exception localException) {
        }
        return defaultValue;
    }

    /**
     * String转换为Double
     *
     * @param str          待转换字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Double strToDouble(String str, Double defaultValue) {
        try {
            defaultValue = Double.parseDouble(str);
        } catch (Exception localException) {
        }
        return defaultValue;
    }

    /**
     * 字符串转换日期
     *
     * @param str          待转换的字符串
     * @param defaultValue 默认日期
     * @return
     */
    public static Date strToDate(String str, Date defaultValue) {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    /**
     * 字符串转换为指定格式的日期
     *
     * @param str          待转换的字符串
     * @param format       日期格式
     * @param defaultValue 默认日期
     * @return
     */
    public static Date strToDate(String str, String format, Date defaultValue) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try {
            defaultValue = fmt.parse(str);
        } catch (Exception localException) {
        }
        return defaultValue;
    }

    /**
     * 日期转换为字符串
     *
     * @param date         待转换的日期
     * @param defaultValue 默认字符串
     * @return
     */
    public static String dateToStr(Date date, String defaultValue) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    /**
     * 日期转换为指定格式的字符串
     *
     * @param date         待转换的日期
     * @param format       指定格式
     * @param defaultValue 默认值
     * @return
     */
    public static String dateToStr(Date date, String format, String defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            defaultValue = sdf.format(date);
        } catch (Exception localException) {
        }
        return defaultValue;
    }

    /**
     * 如果字符串为空则使用默认字符串
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return
     */
    public static String strToStr(String str, String defaultValue) {
        if ((str != null) && (!(str.isEmpty())))
            defaultValue = str;
        return defaultValue;
    }

    /**
     * util date 转换为 sql date
     *
     * @param date
     * @return
     */
    public static java.sql.Date dateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * sql date 转换为 util date
     *
     * @param date
     * @return
     */
    public static Date sqlDateToDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * date 转换为 timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp dateToSqlTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * timestamp 转换为date
     *
     * @param date
     * @return
     */
    public static Date qlTimestampToDate(Timestamp date) {
        return new Date(date.getTime());
    }
}

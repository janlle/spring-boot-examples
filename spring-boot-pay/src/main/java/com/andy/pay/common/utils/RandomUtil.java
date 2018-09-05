package com.andy.pay.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Leone
 * @since: 2018-07-04 16:50
 **/
public class RandomUtil {

    /**
     * 生成随机字符串
     *
     * @author Leone
     * @since: 2018/6/25
     * @params: []
     * @return: java.lang.String
     **/
    public static String getNum(Integer length) {
        if (length <= 0 || length > 512) {
            length = 31;
        }
        StringBuffer result = new StringBuffer();
        final String sources = "0123456789";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            result.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return result.toString();
    }

    /**
     * 生成32位随机数字
     *
     * @author Leone
     * @since: 2018/6/25
     * @params: []
     * @return: java.lang.String
     **/
    public static String getStr(Integer length) {
        if (length <= 0 || length > 32) {
            length = 32;
        }
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }


    /**
     * 获取当前时间戳，单位秒(10位)
     *
     * @author Leone
     * @since: 2018/6/25
     * @params: []
     * @return: java.lang.String
     **/
    public static String timestamp() {
        return System.currentTimeMillis() / 1000 + "";
    }

    public static String currentTimestamp() {
        return System.currentTimeMillis() + "" + new Random().nextInt(9);
    }

    public static void main(String[] args) {
        System.out.println(getStr(34));
        System.out.println(getNum(35));
        System.out.println(currentTimestamp());
        System.out.println(timestamp());
    }


}

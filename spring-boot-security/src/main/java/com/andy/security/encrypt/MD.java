package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Scanner;

/**
 * 消息摘要算法--MD
 * <p>
 * 1.初始化MessageDigest信息摘要对象
 * 2.传入需要计算的字符串更新摘要信息
 * 3.计算信息摘要
 * 4.将byte[] 转换为找度为32位的16进制字符串
 *
 * @author: lyon
 * @since: 2018-07-01 14:36
 **/
public class MD {

    private static final String SRC = "hello";

    public static void main(String[] args) throws Exception {
        String result = MD.MD5(SRC);
        System.out.println(result);
//        test(SRC);
    }

    public static void test(String content) throws Exception {
        byte[] hashCode = content.getBytes("UTF-8");
        for (int i = 0; i < hashCode.length; i++) {
            System.out.println(hashCode[i]);
        }
    }

    /**
     * @author: lyon
     * @since: 2018-07-01 14:52
     * @params: [content]
     * @return: java.lang.String
     **/
    public static String MD2(String content) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            byte[] hashCode = messageDigest.digest(content.getBytes("UTF-8"));
            for (byte b : hashCode) {
                sb.append(Character.forDigit(b >> 4 & 0xf, 16));
                sb.append(Character.forDigit(b & 0xf, 16));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    //Bouncy Castle实现MD4加密
    public static String MD4(String content) {
        String result = null;
        //通过这种方式给JDK动态添加一个provider,就可以通过这种方式获得JDK本身不支持的MD4了
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest messageDigest = MessageDigest.getInstance("MD4");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            result = hexBinaryAdapter.marshal(hashCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @author: lyon
     * @since: 2018-07-01 14:02
     * @params: [input]
     * @return: java.lang.String
     **/
    public static String MD5(String content) {
        //声明StringBuffer对象来存放最后的值
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes("UTF-8"));
            byte[] hashCode = messageDigest.digest();
            return new HexBinaryAdapter().marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
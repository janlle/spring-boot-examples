package com.leone.boot.data.bcrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;

/**
 * <p>
 * 消息摘要算法，MD算法
 * 1.初始化MessageDigest信息摘要对象
 * 2.传入需要计算的字符串更新摘要信息
 * 3.计算信息摘要
 * 4.将byte[] 转换为找度为32位的16进制字符串
 *
 * @author leone
 * @since 2018-07-01
 **/
public class MdExample {

    public static void main(String[] args) throws Exception {
        String content = "hello";
        System.out.println("加密前的内容是: " + content);

        String md5Encode = md5Encode(content);
        String md4Encode = md4Encode(content);
        String md2Encode = md2Encode(content);

        System.out.println("加密后的md5密文是: " + md5Encode);
        System.out.println("加密后的md4密文是: " + md4Encode);
        System.out.println("加密后的md2密文是: " + md2Encode);
    }

    /**
     * MD2摘要算法
     */
    public static String md2Encode(String content) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            byte[] hashCode = messageDigest.digest(content.getBytes(StandardCharsets.UTF_8));
            for (byte b : hashCode) {
                sb.append(Character.forDigit(b >> 4 & 0xf, 16));
                sb.append(Character.forDigit(b & 0xf, 16));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * Bouncy Castle实现MD4加密
     */
    public static String md4Encode(String content) {
        //通过这种方式给JDK动态添加一个provider,就可以通过这种方式获得JDK本身不支持的MD4了
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest messageDigest = MessageDigest.getInstance("MD4");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashCode) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * md5摘要算法
     */
    public static String md5Encode(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] hashCode = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashCode) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }


}
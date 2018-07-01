package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Scanner;

/**
 * 消息摘要算法--MD
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 14:36
 **/
public class MD {

    /**
     * 1.初始化MessageDigest信息摘要对象
     * 2.传入需要计算的字符串更新摘要信息
     * 3.计算信息摘要
     * 4.将byte[] 转换为找度为32位的16进制字符串
     *
     * @author: Mr.lyon
     * @createBy: 2018-07-01 14:02
     * @params: [input]
     * @return: java.lang.String
     **/
    public static String generateMD5(String content) {
        //声明StringBuffer对象来存放最后的值
        StringBuffer sb = new StringBuffer();
        try {
            //1.初始化MessageDigest信息摘要对象,并指定为MD5不分大小写都可以
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            //2.将传入的为指定编码的字节数组
            messageDigest.update(content.getBytes("UTF-8"));
            //3.计算信息摘要digest()方法
            byte[] hashCode = messageDigest.digest();
            //4.将byte[] 转换为找度为32位的16进制字符串
            for (byte b : hashCode) {
                //对数组内容转化为16进制，
                sb.append(Character.forDigit(b >> 4 & 0xf, 16));
                //换2次为32位的16进制
                sb.append(Character.forDigit(b & 0xf, 16));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * @author: Mr.lyon
     * @createBy: 2018-07-01 14:52
     * @params: [content]
     * @return: java.lang.String
     **/
    public static String generateMD2(String content) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            byte[] hashCode = messageDigest.digest(content.getBytes());
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
    public static String generateMD4(String content) {
        StringBuffer sb = new StringBuffer();
        //通过这种方式给JDK动态添加一个provider,就可以通过这种方式获得JDK本身不支持的MD4了
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest messageDigest = MessageDigest.getInstance("MD4");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            for (byte b : hashCode) {
                sb.append(Character.forDigit(b >> 4 & 0xf, 16));
                sb.append(Character.forDigit(b & 0xf, 16));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要加密的内容:");
        String input = scanner.nextLine();
        String result = MD.generateMD4(input);
        System.out.println(result);
    }

}
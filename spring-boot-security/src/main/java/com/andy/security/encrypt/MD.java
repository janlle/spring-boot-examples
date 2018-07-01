package com.andy.security.encrypt;

import java.security.MessageDigest;
import java.util.Scanner;

/*
 * 验证MD5
 * 1.初始化MessageDigest信息摘要对象
 * 2.传入需要计算的字符串更新摘要信息
 * 3.计算信息摘要
 * 4.将byte[] 转换为找度为32位的16进制字符串
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 14:02
 **/
public class MD {

    /**
     * 生成md5 有传入参数字符串
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


    public static String generateMD2(String content) {


        return null;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要加密的内容:");
        String input = scanner.nextLine();
        String result = MD.generateMD5(input);
        System.out.println(result);
    }

}
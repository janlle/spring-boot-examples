package com.andy.security.encrypt;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Scanner;

/**
 * 消息摘要算法--SHA
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 14:10
 **/
public class SHA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要加密的内容:");
        String input = scanner.nextLine();
        String result = SHA.HAA1(input);
        System.out.println(result);
    }

    public static String HAA1(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hash = messageDigest.digest(content.getBytes());
            System.out.println("jdk SHA: " + Hex.encodeHexString(hash));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HAA224(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hash = messageDigest.digest(content.getBytes());
            System.out.println("jdk SHA: " + Hex.encodeHexString(hash));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HAA256(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hash = messageDigest.digest(content.getBytes());
            System.out.println("jdk SHA: " + Hex.encodeHexString(hash));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String HAA384(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hash = messageDigest.digest(content.getBytes());
            System.out.println("jdk SHA: " + Hex.encodeHexString(hash));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String HAA512(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hash = messageDigest.digest(content.getBytes());
            System.out.println("jdk SHA: " + Hex.encodeHexString(hash));
            return Hex.encodeHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

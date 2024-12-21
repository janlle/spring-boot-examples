package com.leone.boot.data.bcrypt;

import java.security.MessageDigest;

/**
 * <p>消息摘要算法-SHA
 *
 * @author leone
 * @since 2018-07-01
 **/
public class ShaExample {

    public static void main(String[] args) {
        String content = "hello";

        String sha1 = sha1(content);        // aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d
        System.out.println(sha1);
        String sha256 = sha256(content);    // 2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
        System.out.println(sha256);
        String sha384 = sha384(content);    // 59e1748777448c69de6b800d7a33bbfb9ff1b463e44354c3553bcdb9c666fa90125a3c79f90397bdf5f6a13de828684f
        System.out.println(sha384);
        String sha512 = sha512(content);    // 9b71d224bd62f3785d96d46ad3ea3d73319bfbc2890caadae2dff72519673ca72323c3d99ba5c11d7c7acc6e14b8c5da0c4663475c2e5c3adef46f73bcdec043
        System.out.println(sha512);
    }

    /**
     * hash1
     */
    public static String sha1(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
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
     * hash256
     */
    public static String sha256(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
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
     * hash384
     */
    public static String sha384(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
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
     * hash512
     */
    public static String sha512(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
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

}

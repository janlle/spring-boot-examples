package com.andy.security.encrypt;

import java.util.Base64;

/**
 * @author Leone
 * @since 2018-05-10
 **/
public class Base_64 {

    private Base_64() {
    }

    private static final String src = "hello world";

    public static void main(String[] args) {
        Base_64.jdk8Base64(src);
    }


    // JDK8 base64实现
    public static void jdk8Base64(String content) {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] encode = encoder.encode(content.getBytes());
            System.out.println("encode:  " + encode);
            String decode = new String(decoder.decode(encode));
            System.out.println("decode:  " + decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
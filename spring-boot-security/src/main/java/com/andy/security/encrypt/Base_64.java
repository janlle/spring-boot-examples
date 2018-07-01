package com.andy.security.encrypt;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base_64 {

    private static String src = "aced";

    public static void main(String[] args) {
        Base_64.jdkBase64(src);
        Base_64.commonsCodecBase64(src);
        Base_64.bouncyCastleBase64(src);
    }

    //使用JDK的base64实现，
    public static void jdkBase64(String content) {
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String encode = encoder.encode(content.getBytes());
            System.out.println("encode:  " + encode);
            String decode = new String(decoder.decodeBuffer(encode));
            System.out.println("decode:  " + decode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //使用apache的commonsCodec实现
    public static void commonsCodecBase64(String content) {
        byte[] encodeBytes = org.apache.commons.codec.binary.Base64.encodeBase64(content.getBytes());
        String encode = new String(encodeBytes);
        System.out.println("encode:  " + encode);

        byte[] decodeBytes = org.apache.commons.codec.binary.Base64.decodeBase64(encode);
        String decode = new String(decodeBytes);
        System.out.println("decode:  " + decode);

    }

    //使用bouncyCastlede实现
    public static void bouncyCastleBase64(String content) {
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(content.getBytes());
        String encode = new String(encodeBytes);
        System.out.println("encode:  " + encode);
        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encode);
        String decode = new String(decodeBytes);
        System.out.println("decode:  " + decode);
    }

}
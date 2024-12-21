package com.leone.boot.data.bcrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;


/**
 * 对称加密算法-DES
 *
 * @author leone
 * @since 2018-07-01
 **/
public class DesExample {

    private static final String KEY = "1234567890123456";

    private final static String IV_PARAMETER = "12345678";

    public static void main(String[] args) {
        String content = "hello";
        System.out.println("加密前的内容是: " + content);

        String encode = desEncode(KEY, content);
        System.out.println("加密后的密文是: " + encode);

        String decode = desDecode(KEY, encode);
        System.out.println("解密后的明文是: " + decode);
    }

    private static Key generateKey(String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(dks);
    }


    public static String desEncode(String password, String content) {
        try {
            Key key = generateKey(password);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return Base64.getEncoder().encodeToString(encodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 解密
     */
    public static String desDecode(String password, String content) {
        try {
            Key key = generateKey(password);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decodeResult = cipher.doFinal(Base64.getDecoder().decode(content.getBytes()));
            return new String(decodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
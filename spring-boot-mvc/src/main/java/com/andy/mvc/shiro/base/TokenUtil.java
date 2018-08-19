package com.andy.mvc.shiro.base;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author: Leone
 * @since: 2018-08-05
 **/
public class TokenUtil {

    private TokenUtil() {
    }

    private static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();

    private static final String rule = "$#@*^";

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public static String encode(String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(rule.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(encodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(rule.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(DatatypeConverter.parseHexBinary(content));
            return new String(decodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(encode("he.llo"));
        System.out.println(decode("DDDB836FC368EA1DD32034FE187617A0"));
//        System.out.println("6166.token".split("\\.").length);
    }

}

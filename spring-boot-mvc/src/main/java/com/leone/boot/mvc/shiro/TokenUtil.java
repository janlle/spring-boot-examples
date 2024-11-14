package com.leone.boot.mvc.shiro;

import jakarta.xml.bind.DatatypeConverter;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author leone
 **/
//@Component
public class TokenUtil {

    private TokenUtil() {
    }

    private static final Logger logger = LoggerFactory.getLogger(TokenRealm.class);

    private static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();

    /**
     * 加密
     *
     * @param content
     * @param rule
     * @return
     */
    public static String encode(String content, String rule) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(rule.getBytes(StandardCharsets.UTF_8));
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(encodeResult);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     * @param rule
     * @return
     */
    public static String decode(String content, String rule) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(rule.getBytes(StandardCharsets.UTF_8));
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(DatatypeConverter.parseHexBinary(content));
            return new String(decodeResult);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String rule = "aaa";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String encode = encode("6166.token" + i, rule);
            System.out.println(encode);
            System.out.println(decode(encode, rule));
        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
        // System.out.println("6166.token".split("\\.").length);
    }

}

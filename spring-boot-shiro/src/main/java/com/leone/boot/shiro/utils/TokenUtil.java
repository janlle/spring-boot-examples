package com.leone.boot.shiro.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author leone
 **/
public class TokenUtil {

    private TokenUtil() {
    }

    private static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static KeyGenerator keyGenerator;

    private static SecureRandom secureRandom;

    static {
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encode(String content, String seed) {
        try {
            keyGenerator.init(128, getInstance(seed));
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
        throw new RuntimeException("Encode failed.");
    }

    public static String decode(String content, String seed) {
        try {
            keyGenerator.init(128, getInstance(seed));
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
        throw new RuntimeException("Decode failed.");
    }

    private static SecureRandom getInstance(String seed) throws NoSuchAlgorithmException {
        if (seed == null) {
            throw new RuntimeException("Seed must be not null!");
        }
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed.getBytes(StandardCharsets.UTF_8));
        return secureRandom;
    }


    public static String[] split(String token) {
        if (token == null) {
            return null;
        }
        return token.split("\\.");
    }

    public static void main(String[] args) {
        String seed = "1290";
        System.out.println(encode("he.llo", seed));
        System.out.println(decode("6EC910EE9AF9632D2725D83C106E80E8", seed));
    }

}

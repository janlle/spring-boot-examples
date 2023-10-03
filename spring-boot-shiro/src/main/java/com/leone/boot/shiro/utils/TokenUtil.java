package com.leone.boot.shiro.utils;

import jakarta.xml.bind.DatatypeConverter;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

/**
 * token: userId.role.randomCode
 *
 * @author leone
 **/
public class TokenUtil {

    private TokenUtil() {
    }

    private static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Random random = new Random();

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
        String[] arr = token.split("\\.");
        if (arr.length != 3) {
            throw new RuntimeException("Ill token");
        }
        return arr;
    }

    public static String[] validateToken(String token, String seed) {
        return split(decode(token, seed));
    }

    public static String randomCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

    public static String generateToken(String userId, String role, String seed) {
        return encode(userId + "." + role + "." + randomCode(), seed);
    }

    public static void main(String[] args) {
        String seed = "1290";
        String token = generateToken("19000", "10", seed);
        System.out.println(token);
        System.out.println(decode(token, seed));
        System.out.println(Arrays.toString(validateToken(token, seed)));
    }

}

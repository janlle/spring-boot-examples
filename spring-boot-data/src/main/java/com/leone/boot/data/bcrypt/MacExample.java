package com.leone.boot.data.bcrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Security;

/**
 * <p>
 * MAC（Message Authentication Codes），是一种消息摘要算法，也叫消息认证码算法。
 * MD系列：HmacMD2、HmacMD4、HmacMD5
 * SHA系列：HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512
 * MAC(Message Authentication Code)，兼容了MD和SHA的特性，并且在它们的基础上加入了密钥。
 * 对于HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512应用的步骤都是一模一样的
 * 因此MAC也称为HMAC（keyed-Hash Message Authentication Code）含有密钥的散列函数算法。
 *
 * @author leone
 * @since 2018-07-01
 **/
public class MacExample {

    private static final String KEY = "b";

    public static void main(String[] args) throws Exception {
        String content = "hello";
        String hmacMd2 = hmacMd2(content);
        System.out.println(hmacMd2);

        String hmacMd4 = hmacMd4(content);
        System.out.println(hmacMd4);

        String hmacMd5 = hmacMd5(content);
        System.out.println(hmacMd5);

        String hmacSha1 = hmacSha1(content);
        System.out.println(hmacSha1);

        String hmacSha224 = hmacSha224(content);
        System.out.println(hmacSha224);

        String hmacSha256 = hmacSha256(content);
        System.out.println(hmacSha256);

        String hmacSha384 = hmacSha384(content);
        System.out.println(hmacSha384);

        String hmacSha512 = hmacSha512(content);
        System.out.println(hmacSha512);

    }

    public static String hmacMd2(String content) {
        try {
            // 添加BouncyCastle的支持
            Security.addProvider(new BouncyCastleProvider());
            SecretKey secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HmacMD2");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            // 执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hmacMd4(String content) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            SecretKey secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HmacMD4");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hmacMd5(String content) {
        try {
            SecretKey secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HmacMD5");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String hmacSha1(String content) {
        try {
            SecretKey secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hmacSha224(String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA224");
            mac.init(new SecretKeySpec(KEY.getBytes(), 0, KEY.length(), "HmacSHA224"));
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String hmacSha256(String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(KEY.getBytes(), 0, KEY.length(), "HmacSHA256"));
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hmacSha384(String content) {
        try {
            SecretKey secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HmacSHA384");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            //初始化mac
            mac.init(secretKeySpec);
            //执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String hmacSha512(String content) {
        try {
            SecretKey secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HmacSHA512");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] digest = mac.doFinal(content.getBytes());
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

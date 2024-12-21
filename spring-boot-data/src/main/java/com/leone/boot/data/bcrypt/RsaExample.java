package com.leone.boot.data.bcrypt;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 非对称加密算法--RSA
 * 公钥加密 私钥解密
 * 私钥加密 公钥解密
 *
 * @author leone
 * @since 2018-05-01
 **/
@Slf4j
public abstract class RsaExample {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4/F9yRVp1xUlb2Os23lkkFjfda9nb0xxUjHlVM5n/N7kug1Mqt0W6+T3S6276sPgXFJSoTYIHLdZ1v5tizMEWX7akutEH/+otopcl7j2+Z/OKT4QR+JAYgQN2Pq3g33azraqMFm8k+m7D9lB0tTnm53TInORIPjHX/qj4PJ4HpQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALj8X3JFWnXFSVvY6zbeWSQWN91r2dvTHFSMeVUzmf83uS6DUyq3Rbr5PdLrbvqw+BcUlKhNggct1nW/m2LMwRZftqS60Qf/6i2ilyXuPb5n84pPhBH4kBiBA3Y+reDfdrOtqowWbyT6bsP2UHS1OebndMic5Eg+Mdf+qPg8ngelAgMBAAECgYEAoLvhb1gltulayazdDIr856dKmWGqJiD0n96DWu4AZEuV432GmTowI7uH5apefwOgPeXLGcZnMje4/g0kRh/lsT/A+l3XrLYosaOifaBBohgcNyysp8d39RtkmasWz3sn2y3ctLSqlt2uPPDUFBgh9v1p2Y3CZYwBOKpJnxfd8GECQQDytbGMXimuPVS+oUQR5J7i+XDccwI/eo5qtWSaNqu7NA9FXqI5VZDlVshfslMIAtLhIEEzWBH4SNbcnxParOCpAkEAwx2BVidCb6CX3XkI5HKu7wMxUkkmirtGXB9WVOMyj7PsqnjG128mLr2ME1aNsfQFlVEfGC2dbAroL03Ds75AnQJAKTnrmqgz9EC+sFK8OT3YLz2nigqPCzFKF54QJJG8weOp5GKas5pxLkN7baXgrK+uGkdcS9hd1QqqVHdA7BgJYQJAHw+WZGGxmNWm93HqMXv0T0Zh1qiggxtXExlGBBN7HBdXiLfbZ7ZhDLXOE9IGkpq3PNMCMTYpxmZiGg22JuoVnQJAbyszLjSEXY8ko/VjsxiYmXWbIrJJ1U/l2mPisLF0RzXAIGGnm8zjibtRtmIGQhUG4Ln+fy7cyZOznc0Cn1jLnA==";

    private static Cipher cipher;
    private static KeyFactory keyFactory;
    private static KeyPairGenerator keyPairGenerator;

    static {
        try {
            cipher = Cipher.getInstance("RSA");
            keyFactory = KeyFactory.getInstance("RSA");
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        String[] keys = generatorKeys("/Users/leone/Downloads", 1024);

        //RSAPublicKey publicKey = loadPublicKey(PUBLIC_KEY);
        //RSAPrivateKey privateKey = loadPrivateKey(PRIVATE_KEY);

        RSAPublicKey publicKey = loadPublicKeyFromFile("/Users/leone/Downloads/pub_key.txt");
        RSAPrivateKey privateKey = loadPrivateKeyFromFile("/Users/leone/Downloads/piv_key.txt");

        String content = "hello";
        System.out.println("加密前内容:" + content);

        String encode = priKeyEncode(content, privateKey);
        System.out.println("私钥加密后内容:" + encode);
        String result = pubKeyDecode(encode, publicKey);
        System.out.println("公钥解密后内容:" + result);

        encode = pubKeyEncode(content, publicKey);
        System.out.println("公钥加密后内容:" + encode);
        result = priKeyDecode(encode, privateKey);
        System.out.println("私钥解密后内容:" + result);

    }

    /**
     * 从文件中加载公钥对象
     */
    public static RSAPublicKey loadPublicKeyFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String key = sb.toString();
            key = key.replace("-----BEGIN RSA PRIVATE KEY-----\n", "");
            key = key.replace("\n-----END RSA PRIVATE KEY-----", "");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 从文件中加载私钥对象
     */
    public static RSAPrivateKey loadPrivateKeyFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String key = sb.toString();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 从字符串中加载公钥对象
     */
    public static RSAPublicKey loadPublicKey(String publicKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 从字符串中加载私钥对象
     */
    public static RSAPrivateKey loadPrivateKey(String privateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 私钥加密
     */
    public static String priKeyEncode(String content, RSAPrivateKey pri_key) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, pri_key);
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 公钥加密
     */
    public static String pubKeyEncode(String content, RSAPublicKey pub_key) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, pub_key);
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 私钥解密
     */
    public static String priKeyDecode(String content, RSAPrivateKey pri_key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(content);
            cipher.init(Cipher.DECRYPT_MODE, pri_key);
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 公钥解密
     */
    public static String pubKeyDecode(String content, RSAPublicKey pub_key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(content);
            cipher.init(Cipher.DECRYPT_MODE, pub_key);
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 生成公钥私钥
     */
    public static String[] generatorKeys(String path, int length) {
        if (length % 64 != 0) {
            throw new RuntimeException("模值长度必须是64的倍数");
        }
        String[] res = new String[2];
        keyPairGenerator.initialize(length, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        try {
            String pubKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String priKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            log.info("pub key {}", pubKey);
            log.info("piv key {}", priKey);
            res[0] = pubKey;
            res[1] = priKey;
            if (path != null) {
                Files.write(Paths.get(path + File.separator + "pub_key.txt"), pubKey.getBytes());
                Files.write(Paths.get(path + File.separator + "piv_key.txt"), priKey.getBytes());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

}
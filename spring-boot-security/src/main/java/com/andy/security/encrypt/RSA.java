package com.andy.security.encrypt;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;


/**
 * 非对称加密算法——RSA
 *
 * @author: lyon
 * @since: 2018-05-01 14:32
 **/
@Slf4j
public class RSA {

    private static String src = "上帝，god gave to something！@#*5";

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    public static void main(String[] args) throws Exception {
        System.out.println("原始字符串：" + src);
        jdkRSA();
    }

    public static void jdkRSA() throws Exception {
        //1.初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);//密钥长度为64的整数倍，最大是65536
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println("RSA公钥：" + encoder.encodeToString(rsaPublicKey.getEncoded()));
        System.out.println("RSA私钥：" + encoder.encodeToString(rsaPrivateKey.getEncoded()));

        //2.1私钥加密，公钥解密【加密】
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("JDK RSA私钥加密：" + encoder.encodeToString(result));

        //2.2私钥加密，公钥解密【解密】
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        result = cipher.doFinal(result);
        System.out.println("JDK RSA公钥解密：" + new String(result));

        //3.1公钥加密，私钥解密【加密】
        x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        result = cipher.doFinal(src.getBytes());
        System.out.println("JDK RSA公钥加密：" + encoder.encodeToString(result));

        //3.2公约加密，私钥解密【解密】
        pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        result = cipher.doFinal(result);
        System.out.println("JDK RSA私钥解密：" + new String(result));
    }





}
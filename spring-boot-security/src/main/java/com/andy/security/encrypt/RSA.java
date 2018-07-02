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
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-01 14:32
 **/
@Slf4j
public class RSA {

    //Base64 编码/解码器 JDK1.8
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    /** 算法名称 */
    private static final String ALGORITHM =  "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    /** 默认密钥大小 */
    private static final int KEY_SIZE = 1024;
    /** 用来指定保存密钥对的文件名和存储的名称 */
    private static final String PUBLIC_KEY_NAME = "publicKey";
    private static final String PRIVATE_KEY_NAME = "privateKey";
    private static final String PUBLIC_FILENAME = "publicKey.properties";
    private static final String PRIVATE_FILENAME = "privateKey.properties";
    /** 密钥对生成器 */
    private static KeyPairGenerator keyPairGenerator = null;
    /** 缓存的密钥对 */
    private static KeyPair keyPair = null;

    private static KeyFactory keyFactory = null;

    /** 初始化密钥工厂 */
    static{
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyFactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(),e);
        }
    }


    public static void main(String[] args) throws Exception {

        String pubFile = "d:/pub.key";
        String priFile = "d:/pri.key";

        makekeyfile(pubFile, priFile);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pubFile));
        RSAPublicKey pubkey = (RSAPublicKey) ois.readObject();
        ois.close();

        ois = new ObjectInputStream(new FileInputStream(priFile));
        RSAPrivateKey prikey = (RSAPrivateKey) ois.readObject();
        ois.close();

        // 使用公钥加密
        String msg = "pass3421li";
        String enc = "UTF-8";

        // 使用公钥加密私钥解密
        System.out.println("原文: " + msg);
        byte[] result = handleData(pubkey, msg.getBytes(enc), 1);
        System.out.println("加密: " + new String(result, enc));
        byte[] deresult = handleData(prikey, result, 0);
        System.out.println("解密: " + new String(deresult, enc));

        msg = "demoiak3";
        // 使用私钥加密公钥解密
        System.out.println("原文: " + msg);
        byte[] result2 = handleData(prikey, msg.getBytes(enc), 1);
        System.out.println("加密: " + new String(result2, enc));
        byte[] deresult2 = handleData(pubkey, result2, 0);
        System.out.println("解密: " + new String(deresult2, enc));

    }


    /**
     * 用私钥对信息生成数字签名
     *
     * @param content    加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(String content, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decoder.decode(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(priKey);
        signature.update(content.getBytes());
        return encoder.encode(signature.sign()).toString();
    }


    /**
     * 校验数字签名
     *
     * @param content   加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(String content, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decoder.decode(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(content.getBytes());
        // 验证签名是否正常
        return signature.verify(decoder.decode(sign));
    }

    public static void makekeyfile(String pubKeyFile, String privateKeyFile) throws Exception {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为1024位  
        keyPairGen.initialize(1024);
        // 生成一个密钥对，保存在keyPair中  
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 得到私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 得到公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 生成私钥  
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
        oos.writeObject(privateKey);
        oos.flush();
        oos.close();

        oos = new ObjectOutputStream(new FileOutputStream(pubKeyFile));
        oos.writeObject(publicKey);
        oos.flush();
        oos.close();
        System.out.println("make file ok!");
    }


    public static byte[] handleData(Key k, byte[] data, int encrypt)
            throws Exception {

        if (k != null) {

            Cipher cipher = Cipher.getInstance("RSA");

            if (encrypt == 1) {
                cipher.init(Cipher.ENCRYPT_MODE, k);
                byte[] resultBytes = cipher.doFinal(data);
                return resultBytes;
            } else if (encrypt == 0) {
                cipher.init(Cipher.DECRYPT_MODE, k);
                byte[] resultBytes = cipher.doFinal(data);
                return resultBytes;
            } else {
                System.out.println("参数必须为: 1 加密 0解密");
            }
        }
        return null;
    }

}
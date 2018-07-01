package com.andy.security.encrypt;

import java.security.*;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * 对称加密算法--AES
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-01 13:56
 **/
public class AES {

    //Base64 编码/解码器 JDK1.8
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    /*
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String Encode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器生成一个128位的随机源,根据传入的字节数组
//            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
            keyGenerator.init(128);
            //3.产生原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] keyBytes = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            Key key = new SecretKeySpec(keyBytes, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return DatatypeConverter.printHexBinary(encodeResult);
//            return Hex.toHexString(encodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 解密
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String Decode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器生成一个128位的随机源,根据传入的字节数组
//            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
            keyGenerator.init(128);
            //3.产生原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] keyBytes = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            Key key = new SecretKeySpec(keyBytes, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] decodeResult = cipher.doFinal(DatatypeConverter.parseHexBinary(content));
            return new String(decodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("使用AES对称加密，请输入加密的规则");
//        String encodeRules = scanner.nextLine();
        System.out.println("请输入要加密的内容:");
        String content = scanner.nextLine();
        System.out.println("根据输入的规则" + encodeRules + "加密后的密文是:" + Encode(encodeRules, content));

//        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
//        encodeRules = scanner.next();
        System.out.println("请输入要解密的内容（密文）:");
        content = scanner.next();
        System.out.println("根据输入的规则" + encodeRules + "解密后的明文是:" + Decode(encodeRules, content));

        jdkAES();
        bcAES();
    }


    private static String encodeRules = "hello";

    private static String content = "hello";

    public static void jdkAES() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
//            keyGenerator.init(128, new SecureRandom("hello".getBytes()));
            //使用上面这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            System.out.println("AESEncode : " + DatatypeConverter.printHexBinary(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(encodeResult);
            System.out.println("AESDecode : " + new String(decodeResult));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void bcAES() {
        try {
            //使用BouncyCastle 的DES加密
            Security.addProvider(new BouncyCastleProvider());
            //生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
            keyGenerator.getProvider();
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");
            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            System.out.println("AESencode : " + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(encodeResult);
            System.out.println("AESdecode : " + new String(decodeResult));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
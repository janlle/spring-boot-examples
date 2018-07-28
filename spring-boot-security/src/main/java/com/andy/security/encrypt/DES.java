package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 对称加密算法--DES
 *
 * @author: lyon
 * @createBy: 2018-07-01 15:37
 **/
public class DES {

    private static String encodeRules = "des";

    private static String content = "hello";

    public static void main(String[] args) {

        String result = DESEncode(encodeRules, content);
        System.out.println("根据输入的规则" + encodeRules + "加密后的密文是:" + result);
        System.out.println("根据输入的规则" + encodeRules + "解密后的明文是:" + DESDecode(encodeRules, result));

        DES.jdkDES(encodeRules, content);
        DES.bcDES(encodeRules, content);
    }


    public static String DESEncode(String encodeRules, String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56, new SecureRandom(encodeRules.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(encodeResult);
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
    public static String DESDecode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            //2.根据encodeRules规则初始化密钥生成器生成一个128位的随机源,根据传入的字节数组
            keyGenerator.init(56, new SecureRandom(encodeRules.getBytes()));
//            keyGenerator.init(56);
            //3.产生原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] keyBytes = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            Key key = new SecretKeySpec(keyBytes, "DES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("DES");
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


    public static void jdkDES(String encodeRules, String content) {

        try {
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            System.out.println("DESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("DESDecode :" + new String(DecodeResult));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void bcDES(String encodeRules, String content) {
        try {

            //使用BouncyCastle 的DES加密
            Security.addProvider(new BouncyCastleProvider());
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //KEY转换
            DESKeySpec deSedeKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(deSedeKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            System.out.println("DESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("DESDecode :" + new String(DecodeResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
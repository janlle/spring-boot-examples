package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * 对称加密算法--DES
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 15:37
 **/
public class DES {

    private static String src = "hello";

    public static void jdkDES() {

        try {
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
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
            byte[] encodeResult = cipher.doFinal(DES.src.getBytes());
            System.out.println("DESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("DESDecode :" + new String(DecodeResult));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void bcDES() {
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
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(DES.src.getBytes());
            System.out.println("DESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("DESDncode :" + new String(DecodeResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DES.jdkDES();
        DES.bcDES();
    }

}
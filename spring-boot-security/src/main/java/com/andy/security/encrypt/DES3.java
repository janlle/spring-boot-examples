package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;


/**
 * 3重DES
 *
 * @author: lyon
 * @since: 2018-07-01 15:37
 **/
public class DES3 {

    /*　　
    3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，NIST将3-DES指定为过渡的加密标准），
    是DES的一个更安全的变形。它以DES为基本模块，通过组合分组方法设计出分组加密算法，
    文本被分成64位大小的文本块然后再进行加密。比起最初的DES，3DES更为安全。其具体实现如下：
    设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的密钥，P代表明文，C代表密文，这样， 　　
    3DES加密过程为：C=Ek3(Dk2(Ek1(P)))
    3DES解密过程为：P=Dk1((EK2(Dk3(C)))
    */

    private static String content = "hello";

    public static void main(String[] args) throws Exception {
        jdkTripleDES(content);
    }

    public static void jdkTripleDES(String content) {
        try {
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //KEY转换
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(deSedeKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            System.out.println("TripleDESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("TripleDESDncode :" + new String(DecodeResult));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void bcTripleDES(String content) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede", "BC");
            keyGenerator.getProvider();
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();


            //KEY转换
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(deSedeKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            System.out.println("TripleDESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("TripleDESDncode :" + new String(DecodeResult));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}  

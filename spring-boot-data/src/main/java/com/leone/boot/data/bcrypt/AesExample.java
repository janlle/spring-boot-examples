package com.leone.boot.data.bcrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 对称加密算法-AES
 * AES(Advanced Encryption Standard)加密算法属于对称加密算法,AES加密算法的安全性要高于DES和3DES, 所以AES已经成为了主要的对称加密算法.
 * AES的加密流程会涉及到AES的五个关键词: 分组密码体制, Padding, 初始向量IV, 密钥, 加密模式
 *
 * @author Leone
 * @since 2018-05-01
 **/
public class AesExample {

    private static final Logger log = LoggerFactory.getLogger(AesExample.class);


    /**
     * 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    /**
     * AES加解密默认密钥(16位，也可以用其它长度，那就用 New 方法 )
     */
    private static final String KEY = "1234567890abcdef";

    private static final String AES = "AES";

    public static void main(String[] args) throws Exception {
        String content = "hello";
        System.out.println("加密前的内容是: " + content);

        String encode = encrypt(content, KEY);
        System.out.println("加密后的密文是: " + encode);

        String decode = decrypt(encode, KEY);
        System.out.println("解密后的明文是: " + decode);
    }

    /**
     * aes 加密
     */
    public static String encrypt(String content, String encryptKey) {
        try {
            KeyGenerator kGen = KeyGenerator.getInstance(AES);
            kGen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), AES));
            byte[] b = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(b);
        } catch (Exception e) {
            log.error("encrypt({} , {})加密异常", content, encryptKey, e);
            return null;
        }
    }

    /**
     * aes 解密
     */
    public static String decrypt(String content, String decryptKey) {
        try {
            KeyGenerator kGen = KeyGenerator.getInstance(AES);
            kGen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), AES));
            byte[] encryptBytes = Base64.getDecoder().decode(content);
            return new String(cipher.doFinal(encryptBytes));
        } catch (Exception e) {
            log.error("decrypt({} , {})解密异常", content, decryptKey, e);
            return null;
        }
    }


}
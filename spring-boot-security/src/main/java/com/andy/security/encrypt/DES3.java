package com.andy.security.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;

/*3DES又称Triple DES，是DES加密算法的一种模式，它使用3条56位的密钥对3DES
 数据进行三次加密。数据加密标准（DES）是美国的一种由来已久的加密标准，它使用对称密钥加密法，并于1981年被ANSI组织规范为ANSI X.3.92。DES使用56位密钥和密码块的方法，而在密码块的方法中，文本被分成64位大小的文本块然后再进行加密。比起最初的DES，3DES更为安全。 　　
3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，NIST将3-DES指定为过渡的加密标准），是DES的一个更安全的变形。它以DES为基本模块，通过组合分组方法设计出分组加密算法，其具体实现如下：
设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的密钥，P代表明文，C代表密文，这样， 　　
3DES加密过程为：C=Ek3(Dk2(Ek1(P)))
3DES解密过程为：P=Dk1((EK2(Dk3(C)))*/
/**
 * 3重DES
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 15:37
 **/
public class DES3 {
  
    // KeyGenerator 提供对称密钥生成器的功能，支持各种算法  
    private KeyGenerator keygen;  
    // SecretKey 负责保存对称密钥  
    private SecretKey deskey;  
    // Cipher负责完成加密或解密工作  
    private Cipher c;  
    // 该字节数组负责保存加密的结果  
    private byte[] cipherByte;  
  
    public DES3() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
        keygen = KeyGenerator.getInstance("DESede");  
        // 生成密钥  
        deskey = keygen.generateKey();  
        // 生成Cipher对象,指定其支持的DES算法  
        c = Cipher.getInstance("DESede");  
    }  
  
    /**  
     * 对字符串加密  
     *   
     * @param str  
     * @return
     */  
    public byte[] Encrytor(String str) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
        c.init(Cipher.ENCRYPT_MODE, deskey);  
        byte[] src = str.getBytes();  
        // 加密，结果保存进cipherByte  
        cipherByte = c.doFinal(src);  
        return cipherByte;  
    }  
  
    /**  
     * 对字符串解密  
     *   
     * @param buff  
     * @return
     */  
    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式  
        c.init(Cipher.DECRYPT_MODE, deskey);  
        cipherByte = c.doFinal(buff);  
        return cipherByte;  
    }  
  
    /**  
     * @param args
     */  
    public static void main(String[] args) throws Exception {  
        DES3 des = new DES3();
        String msg ="james";
        byte[] encontent = des.Encrytor(msg);  
        byte[] decontent = des.Decryptor(encontent);  
        System.out.println("明文是:" + msg);  
        System.out.println("加密后:" + new String(encontent));  
        System.out.println("解密后:" + new String(decontent));  
  
    }

    private static String src = "TestTripleDES";

    public static void jdkTripleDES() {
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
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(src.getBytes());
            System.out.println("TripleDESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("TripleDESDncode :" + new String(DecodeResult));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void bcTripleDES() {

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
            byte[] encodeResult = cipher.doFinal(src.getBytes());
            System.out.println("TripleDESEncode :" + Hex.toHexString(encodeResult));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("TripleDESDncode :" + new String(DecodeResult));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        jdkTripleDES();
//        bcTripleDES();
//    }
  
}  

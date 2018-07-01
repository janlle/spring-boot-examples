package com.andy.security.encrypt;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;
/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-04 09:58
 **/
public class PBE {

    private static String src="Hello PBE";
    public static void jdkPBE(){
        try {
            //初始化盐
            SecureRandom random=new SecureRandom();
            byte[] salt = random.generateSeed(8);   //指定为8位的盐 （盐就是干扰码，通过添加干扰码增加安全）

            //口令和密钥
            String password="lynu";              //口令
            PBEKeySpec pbeKeySpec=new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory=SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key=factory.generateSecret(pbeKeySpec);  //密钥

            //加密
            PBEParameterSpec pbeParameterSpec=new PBEParameterSpec(salt, 100);   //参数规范，第一个参数是盐，第二个是迭代次数（经过散列函数多次迭代）
            Cipher cipher=Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key,pbeParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk PBE加密: "+Base64.encodeBase64String(result));


            //解密
            cipher.init(Cipher.DECRYPT_MODE, key,pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk PBE解密: "+new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

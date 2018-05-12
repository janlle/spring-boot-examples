package com.andy.security.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-04 09:56
 **/
public class SHADemo {
    private static String src="Hello SHA";

    public static void jdkSHA1(){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA");
            digest.update(src.getBytes());
            System.out.println("JDK SHA1: "+Hex.encodeHexString(digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void mcSHA1(){
        Digest digest=new SHA1Digest();
        digest.update(src.getBytes(),0,src.getBytes().length);
        byte[] sha1Byte1=new byte[digest.getDigestSize()];
        digest.doFinal(sha1Byte1, 0);
        System.out.println("MC SHA1:"+org.bouncycastle.util.encoders.Hex.toHexString(sha1Byte1));
    }

}

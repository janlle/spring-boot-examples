package com.andy.security.encrypt;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 14:10
 **/
public class SHA {

    public void jdkSHA(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(content.getBytes());
            byte[] shaBytes = messageDigest.digest();
            System.out.println("jdk SHA: " + Hex.encodeHexString(shaBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

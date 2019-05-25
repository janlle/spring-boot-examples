package com.leone.boot.mvc.sign;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-25
 **/
public abstract class Md5Util {

    /**
     * md5摘要算法
     *
     * @param content 需要签名的算法
     * @return
     */
    public static String MD5(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] hashCode = messageDigest.digest();
            return new HexBinaryAdapter().marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

}

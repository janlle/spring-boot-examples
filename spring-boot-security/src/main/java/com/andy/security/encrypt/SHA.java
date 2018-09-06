package com.andy.security.encrypt;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;

/**
 * <p>消息摘要算法--SHA
 *
 * @author Leone
 * @since 2018-07-01
 **/
public class SHA {

    private static final String SRC = "hello";

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入要加密的内容:");
//        String input = scanner.nextLine();
        String result = SHA.SHA384(SRC);
        System.out.println(result);
    }

    //jdk实现
    public static String SHA1(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            HexBinaryAdapter adapter = new HexBinaryAdapter();
            return adapter.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //非jdk实现
    public static String SHA224(String content) {
        try {
            Digest digest = new SHA224Digest();
            digest.update(content.getBytes(), 0, content.getBytes().length);
            byte[] hashCode = new byte[digest.getDigestSize()];
            digest.doFinal(hashCode, 0);
            HexBinaryAdapter adapter = new HexBinaryAdapter();
            return adapter.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String SHA256(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            return hexBinaryAdapter.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String SHA384(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            return hexBinaryAdapter.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String SHA512(String content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] hashCode = messageDigest.digest(content.getBytes());
            HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
            return hexBinaryAdapter.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.leone.boot.mvc.sign;

/**
 * <p>平台接口的校验需要去每个合作商的数据库表中查询密钥信息进行解密
 *
 * @author leone
 * @since 2019-05-24
 **/
public abstract class SignatureUtil {

    // 接口调用时间和实际时间偏差
    private static final Integer invalidateTime = 180;

    public static void main(String[] args) {
        // 20:33  1558701188
        //        1558701512
        System.out.println(checkTime(1558701188));
        System.out.println(Md5Util.MD5("{\"data\":{\"userId\":1001,\"account\":\"1009\",\"password\":\"1209098\",\"description\":\"hello world\",\"age\":18,\"createTime\":null,\"deleted\":false},\"appId\":\"109098\",\"nonceStr\":\"aaabbad\",\"secret\":\"abc\",\"timestamp\":1558751736}"));
        System.out.println(System.currentTimeMillis()/1000);
    }

    /**
     * 检查当前时间和接口调用时间的是否符合偏差值
     *
     * @param timestamp 接口调用时间
     * @return
     */
    public static boolean checkTime(long timestamp) {
        long currentTime = System.currentTimeMillis() / 1000;
        return !((timestamp + invalidateTime) < currentTime || (timestamp - invalidateTime) > currentTime);
    }


}

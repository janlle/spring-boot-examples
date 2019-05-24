package com.leone.boot.mvc.sign;

import java.security.interfaces.RSAPrivateKey;
import java.util.Objects;

/**
 * <p>平台接口的校验需要去每个合作商的数据库表中查询密钥信息进行解密
 *
 * @author leone
 * @since 2019-05-24
 **/
public abstract class SignatureUtil {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4/F9yRVp1xUlb2Os23lkkFjfda9nb0xxUjHlVM5n/N7kug1Mqt0W6+T3S6276sPgXFJSoTYIHLdZ1v5tizMEWX7akutEH/+otopcl7j2+Z/OKT4QR+JAYgQN2Pq3g33azraqMFm8k+m7D9lB0tTnm53TInORIPjHX/qj4PJ4HpQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALj8X3JFWnXFSVvY6zbeWSQWN91r2dvTHFSMeVUzmf83uS6DUyq3Rbr5PdLrbvqw+BcUlKhNggct1nW/m2LMwRZftqS60Qf/6i2ilyXuPb5n84pPhBH4kBiBA3Y+reDfdrOtqowWbyT6bsP2UHS1OebndMic5Eg+Mdf+qPg8ngelAgMBAAECgYEAoLvhb1gltulayazdDIr856dKmWGqJiD0n96DWu4AZEuV432GmTowI7uH5apefwOgPeXLGcZnMje4/g0kRh/lsT/A+l3XrLYosaOifaBBohgcNyysp8d39RtkmasWz3sn2y3ctLSqlt2uPPDUFBgh9v1p2Y3CZYwBOKpJnxfd8GECQQDytbGMXimuPVS+oUQR5J7i+XDccwI/eo5qtWSaNqu7NA9FXqI5VZDlVshfslMIAtLhIEEzWBH4SNbcnxParOCpAkEAwx2BVidCb6CX3XkI5HKu7wMxUkkmirtGXB9WVOMyj7PsqnjG128mLr2ME1aNsfQFlVEfGC2dbAroL03Ds75AnQJAKTnrmqgz9EC+sFK8OT3YLz2nigqPCzFKF54QJJG8weOp5GKas5pxLkN7baXgrK+uGkdcS9hd1QqqVHdA7BgJYQJAHw+WZGGxmNWm93HqMXv0T0Zh1qiggxtXExlGBBN7HBdXiLfbZ7ZhDLXOE9IGkpq3PNMCMTYpxmZiGg22JuoVnQJAbyszLjSEXY8ko/VjsxiYmXWbIrJJ1U/l2mPisLF0RzXAIGGnm8zjibtRtmIGQhUG4Ln+fy7cyZOznc0Cn1jLnA==";


    // 接口调用时间和实际时间偏差
    private static final Integer invalidateTime = 300;

    public static void main(String[] args) {
        String originData = "{'name':'james','age',18}";

        boolean check = check("mhOjexF37wLbxd2Ba8GxdCvigCgl1Wf2R+YkHR4oo5T9fH9/FuGYtYXle5C90EuPUGnG1wWAy2bxpJUi7BovmFIfqCVDuY9bDBrYdR9RSamu6DOzBhcyGg2q4llZFhrmMzkRZXJJWJq4M2+Vl90IOCeqpMEiVhB31EEPsa5CGCc=", PRIVATE_KEY);
        System.out.println(check);

        // 20:33  1558701188
        //        1558701512
        System.out.println(System.currentTimeMillis() / 1000);

        System.out.println(checkTime(1558701188));


    }

    /**
     * 检查接口数据的签名是否正确
     *
     * @param content    base64编码过的数据
     * @param privateKey 私钥
     * @return
     */
    public static boolean check(String content, String privateKey) {
        RSAPrivateKey rsaPrivateKey = RsaUtil.loadPrivateKeyByStr(privateKey);
        String result = RsaUtil.pri_key_decode(content, rsaPrivateKey);
        return Objects.nonNull(result);
    }


    public String sign(String content, String secret) {

        return null;
    }


    /**
     * 检查当前时间和接口调用时间的是否符合偏差值
     *
     * @param timestamp 接口调用时间
     * @return
     */
    public static boolean checkTime(long timestamp) {
        long currentTime = System.currentTimeMillis() / 1000;
        System.out.println(currentTime);
        return !((timestamp + invalidateTime) < currentTime || (timestamp - invalidateTime) > currentTime);
    }


}

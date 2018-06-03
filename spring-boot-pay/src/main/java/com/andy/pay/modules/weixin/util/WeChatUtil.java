package com.andy.pay.modules.weixin.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.*;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-31 13:31
 **/
@Slf4j
public class WeChatUtil {


    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    /**
     * 微信MD5签名算法
     */
    public static String MD5Sign(String encoding, Map<String, String> params, String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set set = params.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        log.info("生成的字符串签名为:{}-------生成的MD5签名为:{}",sb.toString(), MD5Encode(sb.toString(), encoding).toUpperCase());
        return MD5Encode(sb.toString(), encoding).toUpperCase();
    }



    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charset) {
        String result = null;
        try {
            result = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charset == null || "".equals(charset)) {
                result = byteArrayToHexString(md.digest(result.getBytes()));
            } else {
                result = byteArrayToHexString(md.digest(result.getBytes(charset)));
            }
        } catch (Exception exception) {
            log.info("生成MD5摘要失败！");
        }
        return result;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(SortedMap<String, String> packageParams, String AppKey) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + AppKey);
        String sign = WeChatUtil.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }

    /**
     * 生成 MD5
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        String appid = "wxd930ea5d5a258f4f";
        String mch_id = "10000100";
        String device_info = "1000";
        String body = "test";
        String nonce_str = "ibuaiVcKdpRxkhJA";
        SortedMap<String,String> parameters = new TreeMap<>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("device_info", device_info);
        parameters.put("body", body);
        parameters.put("nonce_str", nonce_str);

        String weixinApiSign = "9A0A8659F005D6984697E2CA0A9CF3B7";
        String mySign = MD5Sign("UTF-8", parameters, "192006250b4c09247ec02edce69f6a2d");
        System.out.println("微信签名是：" + weixinApiSign);
        System.out.println("我的签名是：" + mySign);
        if(weixinApiSign.equals(mySign)){
            System.out.println("恭喜你成功了~");
        }else{
            System.out.println("恭喜你失败了~");
        }
    }


}

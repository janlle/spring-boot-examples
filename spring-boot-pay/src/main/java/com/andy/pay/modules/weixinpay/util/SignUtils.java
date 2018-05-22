package com.andy.pay.modules.weixinpay.util;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 20:59
 **/
@Slf4j
public class SignUtils {

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
        log.info("生成的字符串签名为:{}-------生成的MD5签名为:{}",sb.toString(), MD5Util.MD5Encode(sb.toString(), encoding).toUpperCase());
        return MD5Util.MD5Encode(sb.toString(), encoding).toUpperCase();
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

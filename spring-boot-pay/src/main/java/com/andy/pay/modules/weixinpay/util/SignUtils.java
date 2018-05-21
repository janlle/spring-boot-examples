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
     * 微信签名算法
     * @return
     * @throws Exception
     */
    public static String createSign(String characterEncoding, Map params, String API_KEY) {
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
        sb.append("key=" + API_KEY);
        log.info("生成的字符串签名为:{}", sb.toString());
        log.info("生成的MD5签名为:{}", MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase());
        return MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(">>>模拟微信支付<<<");

        String appid = "wxd930ea5d5a258f4f";
        String mch_id = "10000100";
        String device_info = "1000";
        String body = "test";
        String nonce_str = "ibuaiVcKdpRxkhJA";

        SortedMap<Object,Object> parameters = new TreeMap<>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("device_info", device_info);
        parameters.put("body", body);
        parameters.put("nonce_str", nonce_str);

        String characterEncoding = "UTF-8";
        String weixinApiSign = "9A0A8659F005D6984697E2CA0A9CF3B7";
        System.out.println("微信的签名是：" + weixinApiSign);
        String mySign = createSign(characterEncoding,parameters, "192006250b4c09247ec02edce69f6a2d");
        System.out.println("我的签名是："+mySign);

        if(weixinApiSign.equals(mySign)){
            System.out.println("恭喜你成功了~");
        }else{
            System.out.println("注定了你是个失败者~");
        }
        String userAgent = "Mozilla/5.0(iphone;CPU iphone OS 5_1_1 like Mac OS X) AppleWebKit/534.46(KHTML,like Geocko) Mobile/9B206 MicroMessenger/5.0";
        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
        System.out.println("微信的版本号："+new String(new char[]{agent}));
    }



}

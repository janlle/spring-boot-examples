package com.andy.pay.modules.weixin.util;

import com.andy.pay.common.property.AppProperty;
import com.andy.pay.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-22 13:17
 **/
@Slf4j
public class WeChatPayUtil {


    private static AppProperty appProperty;

    @Autowired
    public WeChatPayUtil(AppProperty appProperty) {
        WeChatPayUtil.appProperty = appProperty;
    }

    private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**微信退参数拼装
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:04
     * @params: [wxOrderId, outTradeNum, totalFee]
     * @return: java.lang.String
     **/
    public static String getWxRefundParam(String wxOrderId, String outTradeNum,String totalFee) {
        String data;
        try {
            String nonceStr = WeChatUtil.genNonceStr();
            SortedMap<String, String> params = new TreeMap<>();
            params.put("appid", appProperty.getWeChat().getAppid());
            params.put("mch_id", appProperty.getWeChat().getMchId());
            params.put("nonce_str", nonceStr);
            params.put("transaction_id", wxOrderId);
            params.put("out_trade_no", outTradeNum);
            params.put("total_fee", totalFee);
            params.put("refund_fee", totalFee);
            params.put("sign", WeChatUtil.createSign("UTF-8", params, appProperty.getWeChat().getApiKey()));
            data = WeChatUtil.mapToXml(params);
        } catch (Exception e) {
            log.error("微信退款参数封装异常！");
            return null;
        }
        return data;
    }

    /**微信退款
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:05
     * @params: [xmlData]
     * @return: void
     **/
    public static boolean wxRefund(String xmlData) throws Exception {
        log.info("****************微信退款开始****************");
        CloseableHttpClient httpClient = WeChatUtil.getHttpsClient(appProperty.getWeChat().getCertPath(), appProperty.getWeChat().getMchId());
        HttpPost httpPost = new HttpPost(REFUND_URL);
        StringEntity entity = new StringEntity(xmlData.toString(), "UTF-8");
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseData = response.getEntity();
        String result = EntityUtils.toString(responseData, "UTF-8");

        log.info("responseXml:{}", result);
        Map<String, String> mapData = WeChatUtil.xmlToMap(result);
        log.info("responseMap:{}", mapData);
        //return_code为微信返回的状态码，SUCCESS表示申请退款成功，return_msg 如非空，为错误原因 签名失败 参数格式校验错误
        if (mapData.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
            log.info("****************退款申请成功**********************");
            //修改订单状态为退款
            return true;
        } else {
            log.info("*****************退款申请失败*********************");
            return false;
        }
    }

    /**向微信发起支付请求
     * @author: Mr.lyon
     * @createBy: 2018/6/3 15:28
     * @params: [requestUrl, xmlData]
     * @return: java.lang.String
     **/
    public static String sendPayRequest(String requestUrl, String xmlData) {
        log.info("****************微信支付开始****************");
        return HttpUtils.sendPostXml(requestUrl, xmlData);
    }

}

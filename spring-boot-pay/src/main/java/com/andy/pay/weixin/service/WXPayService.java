package com.andy.pay.weixin.service;

import com.andy.pay.common.enums.OrderStatusEnum;
import com.andy.pay.common.property.AppProperty;
import com.andy.pay.common.utils.AppUtils;
import com.andy.pay.common.utils.HttpUtils;
import com.andy.pay.common.utils.QRCodeUtil;
import com.andy.pay.common.utils.RandomUtil;
import com.andy.pay.mapper.OrderMapper;
import com.andy.pay.mapper.UserMapper;
import com.andy.pay.pojos.entity.Order;
import com.other.modules.weixinpay.util.HttpUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Leone
 * @since 2018/6/3 16:00
 **/
@Slf4j
@Service
public class WXPayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private static AppProperty appProperty;


    /**
     * App支付
     *
     * @author Leone
     * @since 2018-06-17 12:29
     * @params: [request, orderId]
     * @return: void
     **/
    public void payHandler(HttpServletRequest request, String orderId) {

        List<Order> orderList = orderMapper.selectByOrderId(orderId);
        Order order = orderList.get(0);
        if (order == null) {
            log.error("微信预下单失败，订单不存在orderId:{}", orderId);
            return;
        } else if (order.getStatus() != OrderStatusEnum.CREATE.getStatus()) {
            log.error("微信预下单失败，订单状态不正确orderId:{}", orderId);
            return;
        }
        //微信支付是个必须要传入的参数
        Map<String, String> params = new HashMap<>();
        params.put("appid", appProperty.getWx().getApp_id());                 //appId
        params.put("mch_id", appProperty.getWx().getMch_id());                //微信支付商户号
        params.put("nonce_str", RandomUtil.getStr(12));                      //随机字符串
        params.put("body", "App weChat pay!");                               //商品描述
        params.put("out_trade_no", order.getOutTradeNum());                  //商户订单号
        params.put("total_fee", order.getTotalFee().toString());             //总金额(分)
        params.put("spbill_create_ip", order.getCreateIp());                 //订单生成的机器IP，指用户浏览器端IP
        params.put("notify_url", appProperty.getWx().getNotify_url());        //回调url
        params.put("trade_type", "APP");                                     // 交易类型:JS_API=公众号支付、NATIVE=扫码支付、APP=app支付
        String sign = AppUtils.createSign(params, appProperty.getWx().getApi_key());
        params.put("sign", "sign");
        String xmlData = AppUtils.mapToXml(params);
        log.info("xmlData:{}", xmlData);
        String wxRetXmlData = HttpUtils.sendPostXml(appProperty.getWx().getUrl().getPayUrl(), xmlData);
        log.info("微信返回数据:{}", wxRetXmlData);
        Map<String, String> retData = AppUtils.xmlToMap(wxRetXmlData);
        log.info("微信返回信息:{}", retData);
    }

    /**
     * 微信退款
     *
     * @author Leone
     * @since 2018/6/1 10:20
     * @params: [orderId, userId]
     * @return: java.lang.String
     **/
    public void wxPayRefund(@NonNull String orderId, @NonNull Integer userId) {

        List<Order> order = orderMapper.selectByOrderId(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在！");
        }

        String refundNo = AppUtils.genNonceStr();
        String param = getWxRefundParam("", "", "");
        if (param == null) {
            throw new RuntimeException("参数封装错误！");
        }
        try {
            if (wxRefund(param)) {
                //TODO退款成功
            } else {
                //退款失败
                throw new RuntimeException("微信退款失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信退款异常:{}", e.getMessage());
        }
    }


    /**
     * 微信扫码支付传入的价格为--元
     *
     * @author Leone
     * @since 2018-06-15 11:46
     * @params: [price, userId]
     * @return: void
     **/
    public boolean wxQRCodePay(String price, Integer storeId, HttpServletResponse response, HttpServletRequest request) throws Exception {

        String nonceStr = System.currentTimeMillis() + new Random().nextInt(9) + "";

        Integer intPrice = Integer.valueOf(price);
        String totalFee = intPrice * 1 + "";
        String outTradeNo = System.currentTimeMillis() + "";
        String spbillCreateIp = "45.123.89.22";
        String notifyUrl = "http://rllin.nat300.top/api/cms/code/weChatNotify";

        SortedMap<String, String> params = new TreeMap();
        params.put("appid", appProperty.getWx().getApp_id());
        params.put("mch_id", appProperty.getWx().getMch_id());
        params.put("nonce_str", nonceStr);
        params.put("body", "微信扫码支付");
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee);
        params.put("spbill_create_ip", spbillCreateIp);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", "NATIVE");
        String sign = AppUtils.createSign(params, appProperty.getWx().getApi_key());
        params.put("sign", sign);
        String requestXML = AppUtils.mapToXml(params);
        String responseXml = HttpUtils.sendPostXml(appProperty.getWx().getUrl().getPayUrl(), requestXML);
        Map<String, String> mapResult = AppUtils.xmlToMap(responseXml);
        log.info("微信相应结果为:{}", mapResult);
        //return_code为微信返回的状态码，SUCCESS表示成功，return_msg 如非空，为错误原因 签名失败 参数格式校验错误
        if (mapResult.get("return_code").equalsIgnoreCase("SUCCESS") && mapResult.get("result_code").equalsIgnoreCase("SUCCESS")) {
            log.info("*****************发起预支付成功*********************");
            QRCodeUtil.createQRCode(mapResult.get("code_url"), response);
            //TODO保存订单信息
            return true;
        } else {
            log.info("*****************发起预支付失败*********************");
            return false;
        }
    }

    public void notifyOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO保存支付订单信息
        log.info("收到微信支付回调，保存支付订单信息");
        String resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
        log.info("向微信响应收到支付回调成功");
    }

    /**
     * 微信退参数拼装
     *
     * @author Leone
     * @since 2018/6/3 15:04
     * @params: [wxOrderId, outTradeNum, totalFee]
     * @return: java.lang.String
     **/
    public String getWxRefundParam(String wxOrderId, String outTradeNum, String totalFee) {
        String data;
        try {
            String nonceStr = AppUtils.genNonceStr();
            SortedMap<String, String> params = new TreeMap<>();
            params.put("appid", appProperty.getWx().getApp_id());
            params.put("mch_id", appProperty.getWx().getMch_id());
            params.put("nonce_str", nonceStr);
            params.put("transaction_id", wxOrderId);
            params.put("out_trade_no", outTradeNum);
            params.put("total_fee", totalFee);
            params.put("refund_fee", totalFee);
            params.put("sign", AppUtils.createSign(params, appProperty.getWx().getApi_key()));
            data = AppUtils.mapToXml(params);
        } catch (Exception e) {
            log.error("微信退款参数封装异常！");
            return null;
        }
        return data;
    }

    /**
     * 微信退款
     *
     * @author Leone
     * @since 2018/6/3 15:05
     * @params: [xmlData]
     * @return: void
     **/
    public static boolean wxRefund(String xmlData) throws Exception {
//        log.info("****************微信退款开始****************");
//        CloseableHttpClient httpClient = HttpUtils.sslHttpsClient(appProperty.getWx().getCertPath(), appProperty.getWx().getMch_id());
//        HttpPost httpPost = new HttpPost(appProperty.getWx().getUrl().getRefundUrl());
//        StringEntity entity = new StringEntity(xmlData, "UTF-8");
//        httpPost.setEntity(entity);
//        HttpResponse response = httpClient.execute(httpPost);
//        HttpEntity responseData = response.getEntity();
//        String result = EntityUtils.toString(responseData, "UTF-8");
//
//        log.info("responseXml:{}", result);
//        Map<String, String> mapData = AppUtils.xmlToMap(result);
//        log.info("responseMap:{}", mapData);
//        //return_code为微信返回的状态码，SUCCESS表示申请退款成功，return_msg 如非空，为错误原因 签名失败 参数格式校验错误
//        if (mapData.get("return_code").equalsIgnoreCase("SUCCESS")) {
//            log.info("****************退款申请成功**********************");
//            //修改订单状态为退款
//            return true;
//        } else {
//            log.info("*****************退款申请失败*********************");
//            return false;
//        }
        return false;
    }

    public void appPay(HttpServletRequest request, String orderId) {


    }


    public void appPayRefund(String orderId) {


    }

    public void xcxPay(String orderId, HttpServletRequest request) {
//        String nonce_str = RandomUtil.getNum(24);
//        String spbill_create_ip = AppUtils.getIp(request);
//        if (!AppUtils.isIp(spbill_create_ip)) {
//            spbill_create_ip = "127.0.0.1";
//        }
//        SortedMap<String, String> reqMap = new TreeMap<>();
//        reqMap.put("appid", appProperties.getWx().getApp_id());
//        reqMap.put("mch_id", appProperties.getWx().getMch_id());
//        reqMap.put("nonce_str", nonce_str);
//        reqMap.put("body", "小程序支付");
//        reqMap.put("out_trade_no", outTradeNo);
//        reqMap.put("total_fee", total.toString());
//        reqMap.put("spbill_create_ip", spbill_create_ip);
//        reqMap.put("notify_url", appProperties.getWx().getNotify_url());
//        reqMap.put("trade_type", appProperties.getWx().getTrade_type());
//        reqMap.put("openid", openid);
//        String sign = AppUtils.createSign(reqMap, appProperties.getWx().getApi_key());
//        reqMap.put("sign", sign);
//        String xml = AppUtils.mapToXml(reqMap);
//        log.info("xml:{}", xml);
//        String result = HttpUtil.sendPostXml(appProperties.getWx().getCreate_order(), xml, null);
//        log.info(result);
//        Map<String, String> resData = AppUtils.xmlToMap(result);
//        System.out.println(resData);
//        if ("SUCCESS".equals(resData.get("return_code"))) {
//            Map<String, String> resultMap = new LinkedHashMap<>();
//            //返回的预付单信息
//            String prepay_id = resData.get("prepay_id");
//            System.out.println(prepay_id);
//            resultMap.put("appId", appProperties.getWx().getApp_id());
//            resultMap.put("nonceStr", nonce_str);
//            resultMap.put("package", "prepay_id=" + prepay_id);
//            resultMap.put("signType", "MD5");
//            resultMap.put("timeStamp", RandomUtil.getDateStr(14));
//            String paySign = AppUtils.createSign(resultMap, appProperties.getWx().getApi_key());
//            resultMap.put("paySign", paySign);
//            return resultMap;
//        } else {
//            throw new ValidateException(ExceptionMessage.WEI_XIN_PAY_FAIL);
//        }

    }
}

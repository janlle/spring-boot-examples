package com.andy.pay.modules.weixin.service;

import com.andy.pay.common.enums.OrderStatusEnum;
import com.andy.pay.common.property.AppProperty;
import com.andy.pay.common.utils.HttpUtils;
import com.andy.pay.common.utils.QRCodeUtil;
import com.andy.pay.mapper.OrderMapper;
import com.andy.pay.mapper.UserMapper;
import com.andy.pay.modules.weixin.util.WeChatPayUtil;
import com.andy.pay.modules.weixin.util.WeChatUtil;
import com.andy.pay.object.entity.Order;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author: Mr.lyon
 * @createBy: 2018/6/3 16:00
 **/
@Slf4j
@Service
public class WeChatPayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AppProperty appProperty;

    private static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * App支付
     * @author: Mr.lyon
     * @createBy: 2018-06-17 12:29
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
        params.put("appid", appProperty.getWeChat().getAppid());             //appId
        params.put("mch_id", appProperty.getWeChat().getMchId());            //微信支付商户号
        params.put("nonce_str", WeChatUtil.genNonceStr());                   //随机字符串
        params.put("body", "App weChat pay!");                               //商品描述
        params.put("out_trade_no", order.getOutTradeNum());                  //商户订单号
        params.put("total_fee", order.getTotalFee().toString());             //总金额(分)
        params.put("spbill_create_ip", order.getCreateIp());                 //订单生成的机器IP，指用户浏览器端IP
        params.put("notify_url", appProperty.getWeChat().getNotifyUrl());    //回调url
        params.put("trade_type", "APP");                                     // 交易类型:JSAPI=公众号支付、NATIVE=扫码支付、APP=app支付
        String sign = WeChatUtil.createSign("UTF-8", params, appProperty.getWeChat().getApiKey());
        params.put("sign", "sign");
        String xmlData = WeChatUtil.mapToXml(params);
        log.info("xmlData:{}", xmlData);
        String wxRetXmlData = WeChatPayUtil.sendPayRequest(appProperty.getWeChat().getUrl().getPayUrl(), xmlData);
        log.info("微信返回数据:{}", wxRetXmlData);
        Map<String, String> retData = WeChatUtil.xmlToMap(wxRetXmlData);
        log.info("微信返回信息:{}", retData);
    }

    /**微信退款
     * @author: Mr.lyon
     * @createBy: 2018/6/1 10:20
     * @params: [orderId, userId]
     * @return: java.lang.String
     **/
    public void wxPayRefund(@NonNull String orderId, @NonNull Integer userId) {

        List<Order> order = orderMapper.selectByOrderId(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在！");
        }

        String refundNo = WeChatUtil.genNonceStr();
        String param = WeChatPayUtil.getWxRefundParam("", "", "");
        if (param == null) {
            throw new RuntimeException("参数封装错误！");
        }
        try {
            if (WeChatPayUtil.wxRefund(param)) {
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
     * @author: Mr.lyon
     * @createBy: 2018-06-15 11:46
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
        params.put("appid", appProperty.getWeChat().getAppid());
        params.put("mch_id", appProperty.getWeChat().getMchId());
        params.put("nonce_str", nonceStr);
        params.put("body", "微信扫码支付");
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee);
        params.put("spbill_create_ip", spbillCreateIp);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", "NATIVE");
        String sign = WeChatUtil.createSign("UTF-8", params, appProperty.getWeChat().getApiKey());
        params.put("sign", sign);
        String requestXML = WeChatUtil.mapToXml(params);
        String responseXml = HttpUtils.sendPostXml(URL, requestXML);
        Map<String, String> mapResult = WeChatUtil.xmlToMap(responseXml);
        log.info("微信相应结果为:{}", mapResult);
        //return_code为微信返回的状态码，SUCCESS表示成功，return_msg 如非空，为错误原因 签名失败 参数格式校验错误
        if (mapResult.get("return_code").toString().equalsIgnoreCase("SUCCESS") && mapResult.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
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


}

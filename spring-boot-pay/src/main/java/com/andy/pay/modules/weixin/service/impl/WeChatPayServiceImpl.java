package com.andy.pay.modules.weixin.service.impl;

import com.andy.pay.common.enums.OrderStatusEnum;
import com.andy.pay.mapper.OrderMapper;
import com.andy.pay.modules.weixin.config.AppProperty;
import com.andy.pay.modules.weixin.service.WeChatPayService;
import com.andy.pay.modules.weixin.util.WeChatPayUtil;
import com.andy.pay.modules.weixin.util.WeChatUtil;
import com.andy.pay.object.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: Mr.lyon
 * @createBy: 2018/6/3 16:00
 **/
@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AppProperty appProperty;

    @Override
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
        params.put("trade_type", "APP");                                     // 交易类型JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
        String sign = WeChatUtil.createSign("UTF-8", params, appProperty.getWeChat().getApiKey());
        params.put("sign", "sign");
        String xmlData = WeChatUtil.mapToXml(params);
        log.info("xmlData:{}", xmlData);
        String wxRetXmlData = WeChatPayUtil.sendPayRequest(appProperty.getWeChat().getUrl().getPayUrl(), xmlData);
        log.info("微信返回数据:{}", wxRetXmlData);
        Map<String, String> retData = WeChatUtil.xmlToMap(wxRetXmlData);
        log.info("微信返回信息:{}", retData);
    }

}

package com.andy.pay.modules.weixinpay.service;


import javax.servlet.http.HttpServletRequest;

public interface WeChatPayService {

    void payHandler(HttpServletRequest request, String orderId);

}

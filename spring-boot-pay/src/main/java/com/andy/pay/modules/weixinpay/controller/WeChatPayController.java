package com.andy.pay.modules.weixinpay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 微信支付
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 19:01
 **/
@Controller
@RequestMapping("/pay/weChat")
public class WeChatPayController {


    @GetMapping("/pay")
    public String pay(String orderId) {

        return null;
    }




    @GetMapping("/notify")
    public String notify(String orderId) {

        return null;
    }



}

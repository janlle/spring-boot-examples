package com.andy.pay.modules.weixinpay.controller;

import com.andy.pay.modules.weixinpay.service.WeChatPayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 微信支付
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 19:01
 **/
@Controller
@RequestMapping("/pay/weChat")
public class WeChatPayController {

    @Autowired
    private WeChatPayService weChatPayService;

    @ApiOperation(value = "微信预下单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/pay")
    @ResponseBody
    public String prePay(String orderId, HttpServletRequest request, HttpServletResponse response) {
        weChatPayService.payHandler(request, orderId);
        return "success";
    }

    @GetMapping("/notify")
    public String notify(String orderId) {
        return null;
    }



}

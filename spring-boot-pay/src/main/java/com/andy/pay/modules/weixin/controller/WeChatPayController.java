package com.andy.pay.modules.weixin.controller;

import com.andy.pay.modules.weixin.service.WeChatPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 微信支付
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 19:01
 **/

@Slf4j
@Controller
@Api(tags = "微信支付接口")
@RequestMapping("/wechat")
public class WeChatPayController {

    @Autowired
    private WeChatPayService weChatPayService;

    @ApiOperation(value = "微信预下单", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/pay")
    @ResponseBody
    public String prePay(String orderId, HttpServletRequest request, HttpServletResponse response) {
        log.info("微信预支付开始.....");
        weChatPayService.payHandler(request, orderId);
        return "success";
    }

    @ApiOperation(value = "微信支付回调", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/notify")
    public String notify(String orderId) {
        return null;
    }

    @ApiOperation(value = "微信退款", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/refund")
    public String refund(String orderId) {
        weChatPayService.wxPayRefund(orderId, 12);
        return "success";
    }


    @ApiOperation(value="微信支付主页")
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index() {
        return "weixinpay/index";
    }

}

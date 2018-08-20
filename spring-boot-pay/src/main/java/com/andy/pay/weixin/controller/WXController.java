package com.andy.pay.weixin.controller;

import com.andy.pay.common.property.AppProperty;
import com.andy.pay.common.utils.AppUtils;
import com.andy.pay.weixin.service.WXService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信授权
 * @author: Leone
 * @since: 2018-05-22 20:46
 **/
@Slf4j
@Api(tags = "微信相关接口")
@RestController
@RequestMapping("/wx")
public class WXController {

    @Autowired
    private AppProperty appProperty;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WXService weChatService;

    @ApiOperation(value = "微信授权回调地址", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/auth")
    public String getUserInfo(String state, String code, HttpServletRequest request) {
        log.info("微信授权回调方法,code:{},state:{}", code, state);
        String url = String.format(appProperty.getWx().getUrl().getTokenUrl(), appProperty.getWx().getAppid(), appProperty.getWx().getAppSecret(), code);
        log.info("url:{}", url);
        String result = restTemplate.getForObject(url, String.class);
        log.info("result:{}", result);
        return result;
    }

    @ApiOperation(value = "获取code", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/code")
    public void code(String state, String code, HttpServletRequest request) {
        log.info("获取code.....");
        String url = String.format(appProperty.getWx().getUrl().getAuthCodeUrl(), appProperty.getWx().getAppid(), appProperty.getWx().getRedirectUrl());
        log.info("url:{}", url);
        restTemplate.getForObject(url, String.class);
    }

    @ApiOperation(value = "获取ip", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/ip")
    public void getIp(HttpServletRequest request) {
        String ip = AppUtils.getIp(request);
        log.info("获取ip:{}", ip);
    }


}

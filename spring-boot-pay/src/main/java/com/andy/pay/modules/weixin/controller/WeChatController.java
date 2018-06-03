package com.andy.pay.modules.weixin.controller;

import com.andy.pay.modules.weixin.config.WeChatProperty;
import com.andy.pay.modules.weixin.entity.WeChatUserInfo;
import com.andy.pay.modules.weixin.service.WeChatService;
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
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-22 20:46
 **/
@Slf4j
@Api(tags = "微信相关接口")
@RestController
@RequestMapping("/auth/weChat")
public class WeChatController {

    @Autowired
    private WeChatProperty weChatProperty;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatService weChatService;

    @ApiOperation(value = "微信获取用户信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/user")
    public WeChatUserInfo getUserInfo(String code, HttpServletRequest request, HttpServletResponse response) {
        log.info("获取微信授权码.....");
        return weChatService.getUserInfo();
    }

    @ApiOperation(value = "回信回调url", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/redirect")
    public WeChatUserInfo weChatRedirect(String state , HttpServletRequest request, String code) {
        log.info("公众号授权回调  code : {} state : {}", code, state);
        return weChatService.getUserInfo();
    }

    @ApiOperation(value = "获取code", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/authUrl")
    public String authUrl() {
        String authUrl = String.format(weChatProperty.getWeChatUrls().getAuthCodeUrl(), weChatProperty.getAppId(), weChatProperty.getRedirectUrl());
        log.info("微信获取授权码的url:{}", authUrl);
        return authUrl;
    }


}

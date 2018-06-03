package com.andy.pay.modules.weixin.service.impl;

import com.andy.pay.modules.weixin.config.WeChatProperty;
import com.andy.pay.modules.weixin.entity.WeChatUserInfo;
import com.andy.pay.modules.weixin.service.WeChatService;
import com.andy.pay.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-22 20:49
 **/
@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {


    @Autowired
    private WeChatProperty weChatProperty;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeChatUserInfo getUserInfo() {
        String code = getCode();
        String token = getToken(code);
        Map<String, String> jsonMap = JsonUtils.fromJson(token, Map.class);

        String error = jsonMap.get("errcode");
        String access_token = jsonMap.get("access_token");
        String openId = jsonMap.get("openid");

        String userInfoUrl = String.format(weChatProperty.getUserInfoUrl(), access_token, openId);
        WeChatUserInfo userInfo = restTemplate.getForObject(userInfoUrl, WeChatUserInfo.class);
        log.info("微信获取用户信息的url:{}---->获取的user:{}", userInfoUrl, userInfo);
        return userInfo;
    }

    @Override
    public String getCode() {
        String authUrl = String.format(weChatProperty.getWeChatUrls().getAuthCodeUrl(), weChatProperty.getAppId(), weChatProperty.getRedirectUrl());
        String code = restTemplate.getForObject(authUrl, String.class);
        log.info("微信获取授权码的url:{}---->获取的code:{}", authUrl, code);
        return code;
    }


    @Override
    public String getToken(String code) {
        String tokenUrl = String.format(weChatProperty.getTokenUrl(), weChatProperty.getAppId(), weChatProperty.getAppSecret(), code);
        String token = restTemplate.getForObject(tokenUrl, String.class);
        log.info("微信获取token的url:{}---->获取的token:{}", tokenUrl, token);
        return token;
    }
}

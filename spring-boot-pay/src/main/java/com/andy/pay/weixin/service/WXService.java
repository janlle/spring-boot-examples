package com.andy.pay.weixin.service;

import com.andy.pay.common.property.AppProperty;
import com.andy.pay.common.utils.JsonUtils;
import com.andy.pay.weixin.entity.WXUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Description:
 * @author: lyon
 * @createBy: 2018-05-22 20:49
 **/
@Slf4j
@Service
public class WXService {


    @Autowired
    private AppProperty appProperty;

    @Autowired
    private RestTemplate restTemplate;

    public WXUser getUserInfo() {
        String code = getCode();
        String token = getToken(code);
        Map<String, String> jsonMap = JsonUtils.fromJson(token, Map.class);

        String error = jsonMap.get("errcode");
        String access_token = jsonMap.get("access_token");
        String openId = jsonMap.get("openid");

        String userInfoUrl = String.format(appProperty.getWx().getUrl().getUserInfoUrl(), access_token, openId);
        WXUser userInfo = restTemplate.getForObject(userInfoUrl, WXUser.class);
        log.info("微信获取用户信息的url:{}---->获取的user:{}", userInfoUrl, userInfo);
        return userInfo;
    }

    public String getCode() {
        String authUrl = String.format(appProperty.getWx().getUrl().getAuthCodeUrl(),
                appProperty.getWx().getAppid(), appProperty.getWx().getNotifyUrl());
        String code = restTemplate.getForObject(authUrl, String.class);
        log.info("微信获取授权码的url:{}---->获取的code:{}", authUrl, code);
        return code;
    }


    public String getToken(String code) {
        String tokenUrl = String.format(appProperty.getWx().getUrl().getTokenUrl(), appProperty.getWx().getAppid(), appProperty.getWx().getAppSecret(), code);
        String token = restTemplate.getForObject(tokenUrl, String.class);
        log.info("微信获取token的url:{}---->获取的token:{}", tokenUrl, token);
        return token;
    }
}

package com.andy.pay.modules.weixinpay.service;

import com.andy.pay.modules.weixinpay.entity.WeChatUserInfo;

/**
 *
 */
public interface WeChatService {

    WeChatUserInfo getUserInfo();

    String getCode();

    String getToken(String code);

}

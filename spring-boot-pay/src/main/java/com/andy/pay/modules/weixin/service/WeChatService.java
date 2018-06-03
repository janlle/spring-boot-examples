package com.andy.pay.modules.weixin.service;

import com.andy.pay.modules.weixin.entity.WeChatUserInfo;

/**
 *
 */
public interface WeChatService {

    WeChatUserInfo getUserInfo();

    String getCode();

    String getToken(String code);

}

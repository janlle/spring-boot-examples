package com.andy.pay.modules.weixinpay.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeChatData implements Serializable {

    private static final long serialVersionUID = -254573418180168786L;

    private String orderNum;
    private String prepayId;
    private String appId;
    private String partnerId;
    private String sign;
    private String noncestr;
    private String packagestr;
    private String timestamp;
    private String codeUrl;

}

package com.andy.pay.modules.weixin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeChatPayResponse implements Serializable {

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

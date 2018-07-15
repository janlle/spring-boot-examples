package com.andy.pay.weixin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WXPayResp implements Serializable {

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

package com.andy.pay.modules.weixinpay.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-22 13:09
 **/
@Data
public class WeChatUrls implements Serializable {

    private String prePayUrl;

    private String orderQueryUrl;

    private String closeOrderUrl;

    private String refundUrl;

    private String refundQueryUrl;

    private String downloadBillUrl;

    private String appLoginUrl;

    private String getTokenUrl;

    private String sendServeUrl;

    private String authCodeUrl;

}

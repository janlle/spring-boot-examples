package com.andy.pay.common.property;

import lombok.Data;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-03
 **/
@Data
public class WeChatProperty {

    private WeChatUrlProperty url = new WeChatUrlProperty();

    private String appid;

    private String appSecret;

    private String mchId;

    private String certPath;

    private String apiKey;

    private String notifyUrl;

    private String redirectUrl;

}

package com.andy.pay.common.property;

import lombok.Data;

/**
 * @author: lyon
 * @createBy: 2018-06-03
 **/
@Data
public class WXProperty {

    private WXUrlProperty url = new WXUrlProperty();

    private String appid;

    private String appSecret;

    private String mchId;

    private String certPath;

    private String apiKey;

    private String notifyUrl;

    private String redirectUrl;

}

package com.andy.pay.common.property;

import lombok.Data;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-03 15:38
 **/
@Data
public class WeChatUrlProperty {

    private String payUrl;

    private String orderQueryUrl;

    private String closeOrderUrl;

    private String refundUrl;

    private String refundQueryUrl;

    private String tokenUrl;

    private String userInfoUrl;

    private String authCodeUrl;


}

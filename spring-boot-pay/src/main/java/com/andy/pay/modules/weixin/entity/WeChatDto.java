package com.andy.pay.modules.weixin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-21 17:11
 **/

@Data
public class WeChatDto implements Serializable {

    private String openid;

    private String appid;

    private String mchId;

}

package com.andy.pay.modules.weixin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20 20:34
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "app.pay")
public class AppProperty implements Serializable {

    private AliProperty ali = new AliProperty();

    private WeChatProperty weChat = new WeChatProperty();

}

package com.andy.pay.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-20
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "app.pay")
public class AppProperty  {

    private AliProperty ali = new AliProperty();

    private WeChatProperty weChat = new WeChatProperty();

}

package com.andy.pay.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leone
 * @since 2018-05-20
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "app.pay")
public class AppProperty  {



}

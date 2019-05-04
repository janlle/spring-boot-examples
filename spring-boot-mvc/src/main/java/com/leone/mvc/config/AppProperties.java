package com.leone.mvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leone
 * @since 2018-04-10
 **/
@Component
@ConfigurationProperties(prefix = "app.config")
public class AppProperties {

}

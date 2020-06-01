package com.leone.boot.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

/**
 * @author leone
 * @since 2018-07-16
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "app.token")
public class TokenProperties {

    // token 缓存时长单位分钟
    private Integer duration;

    // redis 中 token 存贮的前缀
    private String redisPrefix;

    // token 的前缀
    private String tokenPrefix;

    // 前端请求的 header 名称
    private String headerName;

    // 秘钥
    private String secret;

}

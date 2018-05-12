package com.andy.mvc.config;

import lombok.Data;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-10 09:46
 **/
@Data
public class RedisConfig {

    private String username;

    private String password;

    private String timeout;

    private String port;

    private String host;

    private String url;


}

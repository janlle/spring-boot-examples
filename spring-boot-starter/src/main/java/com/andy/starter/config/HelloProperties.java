package com.andy.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Mr.ruoLin
 * @since: 2018-05-12 09:28
 **/
@ConfigurationProperties(prefix = "customer")
public class HelloProperties {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
package com.leone.boot.starter.custom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author leone
 * @since 2018-05-12
 **/
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
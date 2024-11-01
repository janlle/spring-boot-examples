package com.leone.boot.starter.custom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-01
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
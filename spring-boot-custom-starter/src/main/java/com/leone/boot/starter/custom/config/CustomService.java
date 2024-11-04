package com.leone.boot.starter.custom.config;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-01
 **/
public class CustomService {

    private CustomProperties customProperties;

    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public String say() {
        return customProperties.getId() + " " + customProperties.getName();
    }

}
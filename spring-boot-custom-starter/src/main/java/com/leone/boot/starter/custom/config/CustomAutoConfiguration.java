package com.leone.boot.starter.custom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-01
 **/
@EnableAutoConfiguration
@ConditionalOnClass(CustomService.class)
@ConditionalOnProperty(prefix = "customer", value = "enabled", matchIfMissing = true)
public class CustomAutoConfiguration {

    @Autowired
    private CustomProperties customProperties;

    @Bean
    @ConditionalOnMissingBean(CustomService.class)
    public CustomService customService() {
        CustomService customService = new CustomService();
        customService.setId(customProperties.getId());
        customService.setName(customProperties.getName());
        return customService;
    }


} 
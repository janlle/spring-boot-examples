package com.leone.boot.starter.custom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leone
 * @since 2018-05-12
 **/
@Configuration
@EnableConfigurationProperties(CustomProperties.class)
@ConditionalOnClass(CustomService.class)
@ConditionalOnProperty(prefix = "customer", value = "enabled", matchIfMissing = true)
public class CustomServiceAutoConfiguration {

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
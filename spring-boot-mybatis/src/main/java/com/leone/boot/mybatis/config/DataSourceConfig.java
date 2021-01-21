package com.leone.boot.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * <p>
 *
 * @author leone
 * @since 2021-01-21
 **/
@Configuration
public class DataSourceConfig {

    @Bean("datasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave1")
    @ConfigurationProperties(prefix = "custom.datasource.slave1")
    public DataSource slave1() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave2")
    @ConfigurationProperties(prefix = "custom.datasource.slave2")
    public DataSource slave2() {
        return DataSourceBuilder.create().build();
    }
}
package com.leone.boot.mvc.lock.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import groovy.transform.ConditionalInterrupt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>
 *
 * @author leone
 * @since 2021-10-08
 **/
@Configuration
@ConditionalOnProperty(name = "mysql.enable", havingValue = "true", matchIfMissing = false)
public class DatabaseLockConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLockConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public HikariDataSource lockDatasource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setDriverClassName(driverClassName);
        config.setUsername(username);
        config.setPassword(password);
        config.setAutoCommit(false);
        config.setMinimumIdle(10);
        config.setMaximumPoolSize(20);
        config.setIdleTimeout(60 * 1000);
        config.setMaxLifetime(30 * 60 * 1000);
        config.setConnectionTimeout(10 * 1000);
        log.info("Init HikariPool Success...");
        return new HikariDataSource(config);
    }


}

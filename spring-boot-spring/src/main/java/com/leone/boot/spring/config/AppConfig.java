package com.leone.boot.spring.config;

import com.leone.boot.spring.life.CustomBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-21
 **/
public class AppConfig {

    @Lazy
    @Scope("prototype")
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public CustomBean customBean() {
        return new CustomBean();
    }

}

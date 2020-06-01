package com.leone.boot.shiro.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *
 * @author leone
 **/
//@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    /**
     * 将自定义拦截器作为Bean写入配置
     *
     * @return
     */
    @Bean
    public TokenInterceptor tokenInterceptor() {
        System.out.println("hello");
        return new TokenInterceptor();
    }

    /**
     * 对拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("world");
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**");
    }


}

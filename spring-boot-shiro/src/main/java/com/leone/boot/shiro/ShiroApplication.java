package com.leone.boot.shiro;

import com.leone.boot.shiro.interceptor.TokenInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author leone
 * @since 2018-04-11
 **/
@SpringBootApplication
@MapperScan("com.leone.boot.shiro.mapper")
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 设置跨域配置,当前默认为允许全部域名,方法get/post/head,不限制header,允许session,有效时间30分
             * @param registry 跨域配置参数
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
                        .excludePathPatterns("/css/**","/img/**","/js/**");
            }
        };
    }

}

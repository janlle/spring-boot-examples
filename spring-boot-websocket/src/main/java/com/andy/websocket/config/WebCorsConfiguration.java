package com.andy.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p> 跨域配置
 *
 * @author leone
 * @since 2019-04-07
 **/
//@Configuration
public class WebCorsConfiguration implements WebMvcConfigurer {


    /**
     * Access-Control-Allow-Origin: http://google.com 表示允许http://google.com 发起跨域请求。
     * Access-Control-Max-Age:86400 表示在86400秒内不需要再发送预校验请求。
     * Access-Control-Allow-Methods: GET,POST,PUT,DELETE 表示允许跨域请求的方法。
     * Access-Control-Allow-Headers: content-type 表示允许跨域请求包含content-type
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        // 1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}

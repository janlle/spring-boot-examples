package com.andy.flux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * 基于Java代码配置启用Spring WebFlux
 *
 * @author ruolin
 */
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /*
     * 配置常用的转换器和格式化配置（与Spring MVC 5配置方式一样）
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 添加日期格式化转换
        DateFormatter dateFormatter = new DateFormatter(DATE_FORMAT);
        registry.addFormatter(dateFormatter);

    }

    /****
     * 资源路径映射配置（与Spring MVC 5一样,只是引入的类不同）
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/public", "classpath:/static/");

    }
}
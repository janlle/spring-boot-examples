package com.andy.pay;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * spring-boot 支付demo
 * nohup java -jar --server.port=8886 spring-boot-pay.jar > web.log &
 */
@Slf4j
@Controller
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.andy.pay.mapper")
public class PayApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cert/**").addResourceLocations("classpath:/cert/");
        super.addResourceHandlers(registry);
    }

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

}
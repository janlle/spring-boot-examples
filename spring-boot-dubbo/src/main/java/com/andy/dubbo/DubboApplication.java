package com.andy.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leone
 * @since 2018-06-03
 **/
@EnableDubboConfiguration
@SpringBootApplication(scanBasePackages = "com.andy.dubbo.provider")
public class DubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class, args);
    }
}

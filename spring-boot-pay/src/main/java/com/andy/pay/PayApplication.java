package com.andy.pay;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * spring-boot 支付demo
 * nohup java -jar --server.port=8886 spring-boot-pay.jar > web.log &
 */
@Slf4j
@Controller
@SpringBootApplication
@MapperScan(basePackages = "com.andy.pay.common.model")
public class PayApplication extends WebMvcConfigurerAdapter {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/cert/**").addResourceLocations("classpath:/cert/");
		super.addResourceHandlers(registry);
	}

	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
		log.info("支付项目启动...");
	}

}
package com.andy.pay;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * java -jar spring-boot-pay.jar --server.port=8886
 * nohup java -jar spring-boot-pay.jar &
 */
@Slf4j
@Controller
@MapperScan(basePackages = "com.andy.pay.common.model")
@SpringBootApplication
public class PayApplication extends WebMvcConfigurerAdapter {

	@RequestMapping("/")
	public String greeting() {
		return "index";
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/cert/**").addResourceLocations(
				"classpath:/cert/");
		super.addResourceHandlers(registry);
		log.info("自定义静态资源目录,这只是个Demo,生产肯定不会暴露");
	}

	public static void main(String[] args) throws Exception{
		SpringApplication.run(PayApplication.class, args);
		//初始化 支付宝-微信-银联相关参数,涉及机密,此文件不会提交,请自行配置相关参数并加载
		//Configs.init("zfbinfo.properties");//支付宝
		//ConfigUtil.init("wxinfo.properties");//微信
		//SDKConfig.getConfig().loadPropertiesFromSrc();//银联
		log.info("支付项目启动...");
	}

}
package com.andy.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ServletComponentScan
@MapperScan("com.andy.mvc.dao.mapper")
@SpringBootApplication
@EnableScheduling       // 整合定时任务需要添加的注解
public class MVCApplication {
	public static void main(String[] args) {
        SpringApplication.run(MVCApplication.class, args);
    }
}

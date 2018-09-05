package com.andy.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Leone
 * @since: 2018-03-23
 **/
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableReactiveMongoRepositories
public class FluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class, args);
    }

//    /****
//     * 配置常用的转换器和格式化配置（与Spring MVC 5配置方式一样）
//     */
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        // 添加日期格式化转换
//        DateFormatter dateFormatter = new DateFormatter(DATE_FORMAT);
//        registry.addFormatter(dateFormatter);
//
//    }

}
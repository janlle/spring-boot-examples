package com.andy.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-05-23 21:32
 **/
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableReactiveMongoRepositories
public class FluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class, args);
    }
}
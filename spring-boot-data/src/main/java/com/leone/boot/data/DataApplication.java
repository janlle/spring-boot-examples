package com.leone.boot.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author leone
 * @since 2018-05-11
 **/
@EnableCaching
@SpringBootApplication
@MapperScan("com.andy.data.repository.mybatis")
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}

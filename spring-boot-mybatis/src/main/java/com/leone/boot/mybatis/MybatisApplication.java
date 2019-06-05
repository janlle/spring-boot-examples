package com.leone.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *com.leone.boot.mybatis.mapper.UserMapper
 * @author leone
 * @since 2018-03-02
 **/
@MapperScan(basePackages = "com.leone.boot.mybatis.mapper")
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}

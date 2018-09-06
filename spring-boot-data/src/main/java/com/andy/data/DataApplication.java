package com.andy.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

/**
 * @author Leone
 * @since 2018-05-11
 **/
@EnableCaching
@SpringBootApplication
@MapperScan("com.andy.data.mybatis.mapper")
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

}

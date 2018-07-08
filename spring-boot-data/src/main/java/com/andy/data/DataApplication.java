package com.andy.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-11 18:42
 **/
@MapperScan("com.andy.data.mybatis.mapper")
@SpringBootApplication
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

    @Bean
    public Converter<String, Date> timestampConvertToDate() {
        Converter<String, Date> timestampConvert = new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                return new Date(new Long(source));
            }
        };
        return timestampConvert;
    }





}

package com.andy.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.JodaTimeConverters;

import java.text.SimpleDateFormat;
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
    public Date dateConvert(String source) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (simpleDateFormat.parse(source));
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

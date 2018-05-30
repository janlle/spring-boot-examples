package com.andy.document;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-29 22:29
 **/
@EnableSwaggerButler
@SpringBootApplication
public class StaticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticApplication.class);
    }
}
package com.andy.spring.config;

import com.andy.spring.beans.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-01 11:17
 **/
@Configuration
public class SpringConfig {


    @Bean
    public Student student() {
        System.out.println("Java config init");
        return new Student();
    }


}

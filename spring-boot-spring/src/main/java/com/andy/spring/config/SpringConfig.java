package com.andy.spring.config;

import com.andy.spring.beans.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lyon
 * @createBy: 2018-07-01 11:17
 **/
@Configuration
public class SpringConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Bean
    public Student student() {
        System.out.println("Java config init");
        return new Student();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


}

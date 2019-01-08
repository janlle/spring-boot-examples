package com.andy.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author leone
 * @since 2018-05-27
 **/
@EnableScheduling
@SpringBootApplication
public class QuartzApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}

package com.andy.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Leone
 * @since 2018-05-27
 **/
@EnableScheduling
@SpringBootApplication
public class TaskApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TaskApplication.class, args);
    }
}

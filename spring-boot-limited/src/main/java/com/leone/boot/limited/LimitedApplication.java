package com.leone.boot.limited;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
@EnableAspectJAutoProxy
@SpringBootApplication
public class LimitedApplication {
    public static void main(String[] args) {
        SpringApplication.run(LimitedApplication.class, args);
    }
}
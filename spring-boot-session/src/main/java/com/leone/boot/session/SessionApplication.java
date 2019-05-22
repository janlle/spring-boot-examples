package com.leone.boot.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author leone
 * @since 2018-05-19
 **/
@EnableRedisHttpSession
@SpringBootApplication
public class SessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SessionApplication.class, args);
    }
}

package com.leone.boot.empty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-11
 **/
@EnableWebSecurity(debug = true)
@SpringBootApplication
public class EmptyApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmptyApplication.class, args);
    }
}

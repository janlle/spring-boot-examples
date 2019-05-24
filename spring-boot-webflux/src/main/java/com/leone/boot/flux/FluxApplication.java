package com.leone.boot.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author leone
 * @since 2018-03-23
 **/
@ServletComponentScan
@SpringBootApplication
//@EnableReactiveMongoRepositories
public class FluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class, args);
    }
}
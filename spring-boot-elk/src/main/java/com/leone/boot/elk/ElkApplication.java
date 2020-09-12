package com.leone.boot.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2020-09-12
 **/
@RestController
@SpringBootApplication
public class ElkApplication {

    private final static Logger logger = LoggerFactory.getLogger(ElkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ElkApplication.class, args);
    }

    @GetMapping("/{name}")
    public String hi(@PathVariable(value = "name") String name) {
        logger.info("name = {}", name);
        return "hi , " + name;
    }
}


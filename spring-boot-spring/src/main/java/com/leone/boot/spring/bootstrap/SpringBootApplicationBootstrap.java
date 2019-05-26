package com.leone.boot.spring.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
// @SpringBootApplication
public class SpringBootApplicationBootstrap {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication();

        Set<String> set = new HashSet<>();
        set.add(ApplicationConfiguration.class.getName());
        springApplication.setSources(set);
        ConfigurableApplicationContext context = springApplication.run(args);

        ApplicationConfiguration bean = context.getBean(ApplicationConfiguration.class);
        System.out.println(bean);

    }

    @SpringBootApplication
    public static class ApplicationConfiguration {

    }
}

package com.leone.boot.learn.bootstrap;

import com.leone.boot.learn.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
//@SpringBootApplication(scanBasePackages = {"com.leone.boot.learn.service"})
public class CalculateServiceBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("java8")
                .run(args);

        CalculateService calculateService = context.getBean(CalculateService.class);

        int result = calculateService.calculate(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(result);


    }
}

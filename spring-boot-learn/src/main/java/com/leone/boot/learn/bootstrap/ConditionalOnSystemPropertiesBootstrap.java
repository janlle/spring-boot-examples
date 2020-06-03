package com.leone.boot.learn.bootstrap;

import com.leone.boot.learn.condition.ConditionalOnSystemProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
//@ConditionalOnSystemProperties(name = "user.name", value = "james")
public class ConditionalOnSystemPropertiesBootstrap {

    @Bean
    @ConditionalOnSystemProperties(name = "user.name", value = "mercy")
    public String helloWorld() {
        return "hello world";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ConditionalOnSystemPropertiesBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        String name = context.getBean("helloWorld", String.class);
        System.out.println(name);

        context.close();
    }

}

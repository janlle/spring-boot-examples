package com.leone.boot.spring.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class FirstApplicationContextInitializer<C extends ConfigurableApplicationContext>
        implements ApplicationContextInitializer<C> {

    @Override
    public void initialize(C application) {
        System.out.println("first Application initialize " + application.getId());
    }


}

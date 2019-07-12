package com.leone.boot.learn.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
public class SecondApplicationContextInitializer implements ApplicationContextInitializer, Ordered {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("second application initialize " + configurableApplicationContext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

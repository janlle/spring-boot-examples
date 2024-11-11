package com.leone.boot.learn.run;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
public class HelloWorldRunListener implements SpringApplicationRunListener {

    public void starting() {
        System.out.println("starting...");
    }


    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }


    public void started(ConfigurableApplicationContext context) {

    }


    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}

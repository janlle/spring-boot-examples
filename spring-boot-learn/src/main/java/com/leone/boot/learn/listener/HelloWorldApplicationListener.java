package com.leone.boot.learn.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("hello world listener " + event.getApplicationContext().getId() + " timestamp: " + event.getTimestamp());
    }
}

package com.leone.boot.learn.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-26
 **/
public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.addApplicationListener(event -> {
            System.err.println("监听到事件" + event);
        });
        context.refresh();

        context.publishEvent("hello world");

        context.close();
    }
}

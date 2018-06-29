package com.andy.mvc.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringManager implements ApplicationListener<ContextRefreshedEvent> {


    private static ApplicationContext applicationContext = null;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(applicationContext == null){
            applicationContext = event.getApplicationContext();
        }
    }

    /*ApplicationContext context= ContextLoader.getCurrentWebApplicationContext();//尝试下这个方法*/
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}

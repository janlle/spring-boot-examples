package com.andy.mvc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener<AppEvent> {

    @Override
    public void onApplicationEvent(AppEvent event) {
        System.out.println("非注解监听器：" + event.getMsg());
    }

}
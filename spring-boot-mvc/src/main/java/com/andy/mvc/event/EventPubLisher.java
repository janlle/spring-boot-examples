package com.andy.mvc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EventPubLisher {


    @Autowired
    private ApplicationContext applicationContext;

    // 事件发布方法
    public void pushListener(String msg) {
        applicationContext.publishEvent(new AppEvent(this, msg));
    }



}
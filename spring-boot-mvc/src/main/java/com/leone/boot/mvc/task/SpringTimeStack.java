package com.leone.boot.mvc.task;

import com.leone.boot.mvc.event.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leone
 * @since 2018-04-10
 **/
@Component
public class SpringTimeStack {

    private static final Logger log = LoggerFactory.getLogger(SpringTimeStack.class);

    @Autowired
    private EventPublisher eventPubLisher;

    @Async
    @Scheduled(cron = "0/60 * * * * ? ")
    public void doSomething() {
        log.info("定时任务开始执行。。。。");
    }

    @Scheduled(cron = "0/60 * * * * ? ")
    public void pushMessage() {
        eventPubLisher.publish("hello 事件消息！");
    }

}

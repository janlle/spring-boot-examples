package com.leone.boot.mvc.task;

import com.leone.boot.mvc.event.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leone
 * @since 2018-04-10
 **/
@Slf4j
@Component
public class SpringTimeStack {

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

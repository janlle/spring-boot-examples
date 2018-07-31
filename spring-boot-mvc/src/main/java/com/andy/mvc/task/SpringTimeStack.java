package com.andy.mvc.task;

import com.andy.mvc.event.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: lyon
 * @since: 2018-04-10 14:14
 **/
@Slf4j
@Component
public class SpringTimeStack {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EventPublisher eventPubLisher;

//    @Scheduled(cron = "0/10 * * * * ? ")
    public void doSomething() {
        log.info("定时任务开始执行。。。。");
    }

    @Scheduled(cron = "0/60 * * * * ? ")
    public void pushMessage(){
        eventPubLisher.publish("hello 事件消息！");
    }

}

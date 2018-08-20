package com.andy.task.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @author: Leone
 * @since: 2018-05-27 09:28
 **/
@Slf4j
//@Component
public class SpringTask {

    /**
     * 固定等待时间 @Scheduled(fixedDelay = 时间间隔 )
     * 固定间隔时间 @Scheduled(fixedRate = 时间间隔 )
     * Corn表达式 @Scheduled(cron = Corn表达式)
     */

    @Scheduled(fixedRate = 1000 * 20)
    public void timeTask() {
        log.info("每20秒执行一个的定时任务！");
    }

    @Scheduled(fixedDelay = 1000 * 40)
    public void DelayTask() {
        log.info("每40秒执行一个的定时任务！");
    }

    
    //每5秒执行一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void doSomething() throws Exception {
        log.info("每5秒执行一个的定时任务："+new Date());
    }


    // 每分钟执行一次
    @Scheduled(cron = "0 0/1 * * * ?")
    public void work() throws Exception {
        log.info("每1分钟执行一个的定时任务："+new Date());
    }
    
    // 每一小时执行一次
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void goWork() throws Exception {
        log.info("每一小时执行一次的定时任务："+new Date());
    }

}

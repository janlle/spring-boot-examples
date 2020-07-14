package com.leone.boot.quartz.config;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;


/**
 * <p>
 *
 * @author leone
 * @since 2018-09-15
 **/
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    public SchedulerExecutorConfig schedulerConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            schedulerConfig.startAllJobs();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }

}
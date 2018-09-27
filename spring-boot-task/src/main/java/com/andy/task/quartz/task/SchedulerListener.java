package com.andy.task.quartz.task;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;


/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    public SchedulerConfig schedulerConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            schedulerConfig.startAllJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
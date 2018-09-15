package com.andy.task.quartz.config;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    public SchedulerConfig schedulerAllJob;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            schedulerAllJob.scheduleJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean();
    }

}
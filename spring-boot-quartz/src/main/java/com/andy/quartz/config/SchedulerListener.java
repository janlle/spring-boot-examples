package com.andy.quartz.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;


/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    public SchedulerExecutorConfig schedulerConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            schedulerConfig.startAllJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
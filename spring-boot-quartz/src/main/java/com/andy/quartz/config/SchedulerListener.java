package com.andy.quartz.config;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

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
    public SchedulerExecutorConfig schedulerConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            schedulerConfig.startAllJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        // Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(20);
        // 设置自定义Job Factory，用于Spring管理Job bean
        factory.setJobFactory(jobFactory);
        return factory;
    }


}
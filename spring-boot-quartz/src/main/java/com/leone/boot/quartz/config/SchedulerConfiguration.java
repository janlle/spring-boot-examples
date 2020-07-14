package com.leone.boot.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 *
 * @author leone
 * @since 2018-06-04
 **/
//@Configuration
//public class SchedulerConfiguration {
//
//
//    @Autowired
//    private QuartzJobFactory jobFactory;
//
//    /**
//     * 调度工工厂
//     *
//     * @return
//     * @throws IOException
//     */
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//        // Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setOverwriteExistingJobs(true);
//        factory.setStartupDelay(20);
//        // 设置自定义Job Factory，用于Spring管理Job bean
//        factory.setJobFactory(jobFactory);
//        factory.setQuartzProperties(quartzProperties());
//        return factory;
//    }
//
//    /**
//     * 读取 classpath 下 quartz 配置项
//     *
//     * @return
//     * @throws IOException
//     */
//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        // 在quartz.properties中的属性被读取并注入后再初始化对象
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }
//
//    /**
//     * quartz初始化监听器
//     * 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
//     */
//    @Bean
//    public QuartzInitializerListener executorListener() {
//        return new QuartzInitializerListener();
//    }
//
//    /**
//     * 通过SchedulerFactoryBean获取Scheduler的实例
//     */
//    @Bean
//    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean().getScheduler();
//    }
//
//
//    @Bean(name = "trigger1")
//    public CronTrigger trigger1() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//        return TriggerBuilder.newTrigger().withIdentity("trigger1", JobConstants.TRIGGER_GROUP)
//                .withSchedule(cronScheduleBuilder).build();
//    }
//
//    @Bean(name = "trigger2")
//    public CronTrigger trigger2() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
//        return TriggerBuilder.newTrigger().withIdentity("trigger2", JobConstants.TRIGGER_GROUP)
//                .withSchedule(cronScheduleBuilder).build();
//    }
//
//}

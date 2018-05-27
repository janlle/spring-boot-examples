package com.andy.task.quartz.config;

import com.andy.task.quartz.jobs.HelloJob;
import com.andy.task.quartz.jobs.SchedulingTaskA;
import com.andy.task.quartz.jobs.SpringJobFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

    @Autowired
    private SpringJobFactory springJobFactory;

    /**
     * 配置定时任务
     * ScheduleTask为需要执行的任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(HelloJob task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 设置任务的名字
        jobDetail.setName("overTimeNoticeJob");
        // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        jobDetail.setGroup("overTimeNoticeJobGroup");
        //为需要执行的实体类对应的对象
        jobDetail.setTargetObject(task);
        //添加需要执行的方法
        //通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的需要执行方法
        jobDetail.setTargetMethod("execute");
        return jobDetail;
    }

    /**
     * 配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        //初始时的cron表达式  ，没5分钟执行一次
        tigger.setCronExpression("* 0/5 * * * ?");
        //trigger的name
        tigger.setName("executeTask");
        return tigger;

    }

    /**
     * 定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setJobFactory(springJobFactory);
//        return schedulerFactoryBean;
//    }
//
//    @Bean
//    public Scheduler scheduler() {
//        return schedulerFactoryBean().getScheduler();
//    }















} 
package com.andy.task.quartz.config;

import com.andy.task.quartz.jobs.HelloJob;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author: lyon
 * @since: 2018-07-19
 **/
@Configuration
public class QuartzConfig {

    /**
     * 配置定时任务
     * ScheduleTask为需要执行的任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(HelloJob task) {
        MethodInvokingJobDetailFactoryBean detailFactory = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        detailFactory.setConcurrent(false);
        // 设置任务的名字
        detailFactory.setName("overTimeNoticeJob");
        // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        detailFactory.setGroup("overTimeNoticeJobGroup");
        //为需要执行的实体类对应的对象
        detailFactory.setTargetObject(task);
        //添加需要执行的方法
        //通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的需要执行方法
        detailFactory.setTargetMethod("execute");
        return detailFactory;
    }

    /**
     * 配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean detailFactoryBean) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(detailFactoryBean.getObject());
        //初始时的cron表达式  ，没5分钟执行一次
        trigger.setCronExpression("* 0/5 * * * ?");
        //trigger的name
        trigger.setName("executeTask");
        return trigger;

    }

    /**
     * 定义quartz调度工厂
     */
    @Bean(name = "jobSchedule")
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


}
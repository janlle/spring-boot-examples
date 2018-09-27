package com.andy.task.quartz.config;


import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
@Component
public class SchedulerConfig {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 该方法用来启动所有的定时任务
     *
     * @throws SchedulerException
     */
    public void startAllJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob1(scheduler);
        scheduleJob2(scheduler);
    }

    /**
     * 配置Job1
     *
     * @param scheduler
     * @throws SchedulerException
     */
    private void scheduleJob1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ActivityJob.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 配置Job
     *
     * @param scheduler
     * @throws SchedulerException
     */
    private void scheduleJob2(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(CollectJob.class).withIdentity("job2", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

}
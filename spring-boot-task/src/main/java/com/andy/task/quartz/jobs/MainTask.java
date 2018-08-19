package com.andy.task.quartz.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author: lyon
 * @since: 2018-06-05
 **/
public class MainTask {

    public static void main(String[] args) throws Exception {
        //创建jobDetail实例
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job-a", "group-a").build();

        //创建trigger实例
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-a", "group-a").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        //创建schedule实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }

}

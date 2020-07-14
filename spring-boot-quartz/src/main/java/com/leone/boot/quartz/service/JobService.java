package com.leone.boot.quartz.service;

import com.leone.boot.quartz.config.JobConstants;
import com.leone.boot.quartz.jobs.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-13
 **/
@Service
public class JobService {

    @Autowired
    private Scheduler scheduler;

//    @Autowired
//    @Qualifier("trigger1")
//    private CronTrigger cronTrigger1;

    public void addJob() throws SchedulerException {
        // 创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("j3", JobConstants.JOB_GROUP).build();
        jobDetail.getJobDataMap().put("hello", "world");

        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//
//        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("t1", JobConstants.TRIGGER_GROUP)
                .withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void updateJob() {

    }

    public void selectJob() {
        TriggerKey triggerKey = TriggerKey.triggerKey("job4", "group3");

    }

    public void deleteJob() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("j1", JobConstants.JOB_GROUP);
        scheduler.pauseTrigger(triggerKey);//停止触发器
        scheduler.unscheduleJob(triggerKey);//移除触发器
        scheduler.deleteJob(JobKey.jobKey("j1", JobConstants.JOB_GROUP));//删除任务
    }

    public void pauseJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("j2", JobConstants.JOB_GROUP);
        scheduler.pauseJob(jobKey);

    }


    /**
     * 启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

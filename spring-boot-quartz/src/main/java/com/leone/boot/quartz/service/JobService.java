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

    /**
     * 添加一个job
     *
     * @throws SchedulerException
     */
    public void addJob() throws SchedulerException {
        // 创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("j4", JobConstants.JOB_GROUP).build();
        jobDetail.getJobDataMap().put("hello", "world");

        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

//        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("t4", JobConstants.TRIGGER_GROUP)
                .withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 更新job
     */
    public void updateJob(String cron) {
        TriggerKey triggerKey = TriggerKey.triggerKey("j1", JobConstants.JOB_GROUP);

        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectJob() {
        TriggerKey triggerKey = TriggerKey.triggerKey("job4", "group3");

    }

    public void deleteJob() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("t4", JobConstants.JOB_GROUP);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey("j4", JobConstants.JOB_GROUP));
    }

    /**
     * 暂停某个job
     *
     * @throws SchedulerException
     */
    public void pauseJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("j3", JobConstants.JOB_GROUP);
        scheduler.pauseJob(jobKey);
    }

    public void startJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("j3", JobConstants.JOB_GROUP);
        scheduler.resumeJob(jobKey);
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

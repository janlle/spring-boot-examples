package com.leone.boot.quartz.service;

import com.leone.boot.quartz.config.JobConstants;
import com.leone.boot.quartz.jobs.SimpleJob;
import com.leone.boot.quartz.repository.JobInfo;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-13
 **/
@Service
public class JobService {

    // 调度器实例
    @Autowired
    private Scheduler scheduler;

    /**
     * 添加一个job
     */
    public void addJob(String jobName, String jobGroup, String jobClassName, Map<String, Object> param, String cron, String jobDesc) throws SchedulerException {
        JobInfo job = new JobInfo();
        job.setJobName(jobName);
        job.setJobGroup(jobGroup);
        job.setGmtCreate(new Date());
        job.setJobCron("");
        job.setJobStatus(1);

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

        if (scheduler.checkExists(jobKey)) {
            throw new RuntimeException("该定时任务不存在：" + jobKey.getName());
        }

        Class<? extends Job> jobClass;
        try {
            Class<?> clazz = Class.forName(jobClassName);
            if (clazz.isAssignableFrom(Job.class)) {
                throw new RuntimeException("类型不匹配：" + jobClassName);
            }
            jobClass = (Class<? extends Job>) clazz;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到任务类：" + jobClassName);
        }

        JobDataMap jobDataMap = new JobDataMap();
        if (param != null) {
            jobDataMap.putAll(param);
        }

        // 1.创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
          .withIdentity(jobName, jobGroup)
          .usingJobData(jobDataMap)
          .build();

        // 2.TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder
          .newTrigger()
          .withIdentity("t4", JobConstants.TRIGGER_GROUP)
          .withSchedule(CronScheduleBuilder.cronSchedule(cron)) // 基于表达式构建触发器
          .withDescription(jobDesc)
          .build();

        // 3.调度任务
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

    public void listJob() {

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

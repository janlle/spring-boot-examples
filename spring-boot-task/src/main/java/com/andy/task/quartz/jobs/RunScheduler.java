package com.andy.task.quartz.jobs;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

//@Component
public class RunScheduler {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	private static Scheduler scheduler;

	public void scheduleJobs() throws SchedulerException {
		scheduler = schedulerFactoryBean.getScheduler();
		startJobA(); // 每15分钟执行一次
		startJobB(); // 每两小时执行一次
	}

	public static void startJobA() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(TaskA.class).withIdentity("job-a", "group-a").build();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/15 * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger-a", "group-a").withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}
	public static void modifyJobA(String cron) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger-a", "group-a");
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity("trigger-a", "group-a")
				.withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, newTrigger);
	}
	public static String getJob1Status() throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger-a", "group-a");
		return scheduler.getTriggerState(triggerKey).name();
	}
	public static void pauseJob1() throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey("job-a", "group-a"));
	}
	public static void resumeJob1() throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey("job-a", "group-a"));
	}

	private void startJobB() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(TaskB.class).withIdentity("job2", "group1").build();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0/2 * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
				.withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}
	public static void modifyJob2(String cron) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger2", "group1");
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
				.withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, newTrigger);
	}
	public static String getJob2Status() throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger2", "group1");
		return scheduler.getTriggerState(triggerKey).name();
	}
	public static void pauseJob2() throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey("job2", "group1"));
	}
	public static void resumeJob2() throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey("job2", "group1"));
	}

}
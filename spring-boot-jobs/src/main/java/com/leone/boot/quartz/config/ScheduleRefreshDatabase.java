package com.leone.boot.quartz.config;

import org.quartz.*;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;


/**
 * <p>
 *
 * @author leone
 * @since Friday September
 **/
//@EnableScheduling
public class ScheduleRefreshDatabase {

    @Resource
    private DataSource dataSource;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Scheduled(fixedRate = 5000) // 每隔5s查库，并根据查询结果决定是否重新设置定时任务
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();// 当前Trigger使用的
        //String searchCron = repository.findById(1).get().getCron();// 从数据库查询出来的
        String searchCron = "";
        System.out.println(currentCron);
        System.out.println(searchCron);
        if (currentCron.equals(searchCron)) {
            // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
        } else {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                    .withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
            currentCron = searchCron;
        }
    }
}

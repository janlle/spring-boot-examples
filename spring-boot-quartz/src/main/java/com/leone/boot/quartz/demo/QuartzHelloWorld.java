package com.leone.boot.quartz.demo;

import com.leone.boot.quartz.jobs.JobA;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @author leone
 * @since 2018-05-19
 **/
public class QuartzHelloWorld {

    public static void main(String[] args) {
        try {
            //定义一个Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    // 定义name/group
                    .withIdentity("trigger1", "group1")
                    // 一旦加入scheduler，立即生效
                    .startNow()
                    // 使用SimpleTrigger
                    .withSchedule(simpleSchedule()
                            // 每隔一秒执行一次
                            .withIntervalInSeconds(1)
                            // 上帝之手一直执行
                            .repeatForever())
                    .build();

            //定义一个JobDetail
            JobDetail job = JobBuilder.newJob(JobA.class)
                    //定义Job类为HelloQuartz类，这是真正的执行逻辑所在定义name/group
                    .withIdentity("job1", "group1")
                    //定义属性
                    .usingJobData("name", "quartz")
                    .build();

            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //加入这个调度
            scheduler.scheduleJob(job, trigger);
            //启动之
            scheduler.start();
            //运行一段时间后关闭
            Thread.sleep(3000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
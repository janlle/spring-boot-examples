package com.leone.boot.quartz.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * @author leone
 * @since 2018-05-19
 **/
public class QuartzDemo {

    public static void main(String[] args) {
        try {
            // 1.定义一个Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
              // 定义name/group
              .withIdentity("t01", "g01")
              // 一旦加入scheduler，立即生效
              .startNow()
              // 使用SimpleTrigger
              .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                // 每隔一秒执行一次
                .withIntervalInSeconds(1)
                // 一直执行
                .repeatForever())
              .build();

            //2.定义一个JobDetail
            JobDetail job = JobBuilder.newJob(DemoJob.class)
              //定义Job类为HelloQuartz类，这是真正的执行逻辑所在定义name/group
              .withIdentity("j01", "g02")
              //定义属性
              .usingJobData("name", "kobe")
              .build();

            //3.创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //加入这个调度
            scheduler.scheduleJob(job, trigger);
            //启动之
            scheduler.start();
            //运行一段时间后关闭
            Thread.sleep(10000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
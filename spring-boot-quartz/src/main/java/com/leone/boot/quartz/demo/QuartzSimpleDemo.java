package com.leone.boot.quartz.demo;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;


public class QuartzSimpleDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = new JobDetailImpl();
        Trigger trigger = new SimpleTriggerImpl();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        scheduler.shutdown();
    }
}

class SayHelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello-" + Thread.currentThread().getName());
    }

}
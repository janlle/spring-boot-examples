package com.leone.boot.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-14
 **/
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Object hello = jobDetail.getJobDataMap().get("hello");
        System.out.println("this is simple job " + jobDetail.getKey().getName() + " " + LocalDateTime.now() + " " + hello.toString());

    }

}

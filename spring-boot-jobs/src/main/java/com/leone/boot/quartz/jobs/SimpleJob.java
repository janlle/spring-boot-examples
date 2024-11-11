package com.leone.boot.quartz.jobs;

import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-14
 **/
//@DisallowConcurrentExecution
//@Component
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Object hello = jobDetail.getJobDataMap().get("hello");
        System.out.println("this is simple job " + jobDetail.getKey().getName() + " " + LocalDateTime.now() + " " + hello.toString());

    }

}

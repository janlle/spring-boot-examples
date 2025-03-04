package com.leone.boot.quartz.demo;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * @author leone
 * @since 2025-03-04
 **/
public class DemoJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(DemoJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        log.info("DemoJob {} {}", jobDetail.getKey().getName(), jobDetail.getJobDataMap());
    }
}
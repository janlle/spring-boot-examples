package com.leone.boot.quartz.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-14
 **/
//@DisallowConcurrentExecution
//@Component
public class SimpleJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(SimpleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        log.info("SimpleJob {} {}", jobDetail.getKey().getName(), jobDetail.getJobDataMap());
    }

}

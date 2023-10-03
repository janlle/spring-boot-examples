package com.leone.boot.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leone
 * @since 2018-05-27
 **/
@Component
@DisallowConcurrentExecution
public class JobA implements Job {

    private static final Logger log = LoggerFactory.getLogger(JobA.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("spring-Quartz hello world---A! now:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }


}

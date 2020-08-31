package com.leone.boot.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leone
 * @since 2018-05-27
 **/
@Slf4j
@Component
@DisallowConcurrentExecution
public class JobA implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("spring-Quartz hello world---A! now:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }


}

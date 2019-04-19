package com.leone.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Leone
 * @since 2018-05-19
 **/
@Slf4j
@Component
public class JobB implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("spring-Quartz hello world---B! now:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
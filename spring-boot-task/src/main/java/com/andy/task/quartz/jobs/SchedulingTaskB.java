package com.andy.task.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulingTaskB implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("spring-Quartz hello world---B! now:"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) );
    }


}
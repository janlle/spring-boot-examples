package com.leone.batch.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p> 定时任务
 *
 * @author Leone
 * @since 2018-10-09
 **/
@Service
public class ScheduledTaskService {

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private Job importJob;

    private JobParameters jobParameters;

    @Scheduled(fixedRate = 5000)
    public void execute() throws Exception {
        jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(importJob, jobParameters);
    }


}
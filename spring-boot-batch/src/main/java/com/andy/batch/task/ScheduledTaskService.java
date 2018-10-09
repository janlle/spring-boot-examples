package com.andy.batch.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * <p> 定时任务
 *
 * @author Leone
 * @since 2018-10-09
 **/
@Service
public class ScheduledTaskService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    public JobParameters jobParameters;

    @Scheduled(fixedRate = 5000)
    public void execute() throws Exception {
        jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(importJob, jobParameters);
    }

}
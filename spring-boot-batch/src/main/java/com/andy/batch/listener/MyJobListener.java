
package com.andy.batch.listener;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        //Job开始前
        System.out.println("Job开始前");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //Job完成后
        System.out.println("Job完成后");
    }


}


package com.leone.boot.quartz.service;

import com.leone.boot.quartz.jobs.SimpleJob;
import com.leone.boot.quartz.repository.JobBean;
import com.leone.boot.quartz.repository.JobRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-13
 **/
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Scheduler scheduler;


    public void addJob(SimpleJob job) {

    }

    public void updateJob() {

    }

    public void selectJob() {

    }

    public void deleteJob() {

    }


}

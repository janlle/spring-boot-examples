package com.leone.boot.quartz.controller;

import com.leone.boot.quartz.config.JobConstants;
import com.leone.boot.quartz.jobs.JobA;
import com.leone.boot.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author leone
 * @since 2020-07-14
 **/
@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    @GetMapping("/save")
    public String save() {
        jobService.addJob(JobA.class, "job_" + System.currentTimeMillis(), JobConstants.JOB_GROUP
                , "trigger_" + System.currentTimeMillis(), JobConstants.TRIGGER_GROUP, JobConstants.JOB_CRON, null);
        return "success";
    }

    @GetMapping("/delete")
    public String delete() {
        jobService.deleteJob("job_", JobConstants.JOB_GROUP, "trigger_", JobConstants.TRIGGER_GROUP);
        return "success";
    }

    @GetMapping("/pause")
    public String pause() {
        jobService.pauseJob("job_", JobConstants.JOB_GROUP);
        return "success";
    }

    @GetMapping("/start")
    public String start() {
        jobService.startJob("job_", JobConstants.JOB_GROUP);
        return "success";
    }


}

package com.leone.boot.quartz.controller;

import com.leone.boot.quartz.config.JobConstants;
import com.leone.boot.quartz.jobs.JobA;
import com.leone.boot.quartz.service.JobService;
import org.quartz.SchedulerException;
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
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    @GetMapping("/save")
    public String save() throws SchedulerException {
        jobService.addJob();
        return "success";
    }

    @GetMapping("/delete")
    public String delete() throws SchedulerException {
        jobService.deleteJob();
        return "success";
    }

    @GetMapping("/pause")
    public String pause() throws SchedulerException {
        jobService.pauseJob();
        return "success";
    }

    @GetMapping("/start")
    public String start1() throws SchedulerException {
        jobService.startJob();
        return "success";
    }

    @GetMapping("start")
    public String start2() throws SchedulerException {
        jobService.startJob();
        return "success";
    }


}

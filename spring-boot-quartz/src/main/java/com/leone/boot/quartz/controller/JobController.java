package com.leone.boot.quartz.controller;

import com.leone.boot.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private JobService jobService;

    @GetMapping("save")
    public String save() throws SchedulerException {
        jobService.addJob();
        return "success";
    }

    @GetMapping("delete")
    public String delete() throws SchedulerException {
        jobService.deleteJob();
        return "success";
    }

    @GetMapping("pause")
    public String pause() throws SchedulerException {
        jobService.pauseJob();
        return "success";
    }


}

package com.leone.boot.quartz.controller;

import com.leone.boot.quartz.service.JobService;
import jakarta.annotation.Resource;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * <p>
 *
 * @author leone
 * @since 2020-07-14
 **/
@RestController
public class JobController {

    @Resource
    private JobService jobService;

    @GetMapping("/save")
    public String save() throws SchedulerException {
        jobService.addJob(
          "testJob",
          "testGroup",
          "com.leone.boot.quartz.jobs.SimpleJob",
          Map.of("day", 1),
          "0/10 * * * * ? ",
          "测试任务"
        );
        return "success";
    }

    @GetMapping("/delete")
    public String delete() throws SchedulerException {
        //jobService.deleteJob();
        return "success";
    }

    @GetMapping("/pause")
    public String pause() throws SchedulerException {
        //jobService.pauseJob();
        return "success";
    }

    @GetMapping("/start1")
    public String start1() throws SchedulerException {
        //jobService.startJob();
        return "success";
    }

}

package com.andy.log.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-12 09:12
 **/


public class LogTask {

    private static final Logger logger = LoggerFactory.getLogger(LogTask.class);

//    @Scheduled("*")
//    @Schedules()
    public void genLog() {
        logger.info("");

    }

}

package com.andy.log.task;

import com.andy.log.util.RandomAppLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-20
 **/
@Component
public class AppLogTask {

    @Resource
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger JSON_LOG = LoggerFactory.getLogger("json-log");

    private static String url = "http://localhost:8080/coll/index";

    private static Random random = new Random();

    /**
     * 用户画像
     *
     * @Scheduled(fixedDelay = 50)
     */
    @Async
    @Scheduled(fixedDelay = 50)
    public void commonLog() {
        JSON_LOG.info(RandomAppLog.randomAppLog());
    }


}

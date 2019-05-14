package com.leone.boot.log.task;

import com.leone.boot.log.util.RandomAppLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-20
 **/
@Component
public class AppLogTask {

    private static final Logger JSON_LOG = LoggerFactory.getLogger("json-log");

    private static String url = "http://localhost:8080/coll/index";

    private static Random random = new Random();

    /**
     * 用户画像
     *
     * @Scheduled(fixedDelay = 50)
     */
    @Async
//    @Scheduled(fixedDelay = 50)
    public void commonLog() {
        JSON_LOG.info("{}", RandomAppLog.randomAppLog());
    }


}

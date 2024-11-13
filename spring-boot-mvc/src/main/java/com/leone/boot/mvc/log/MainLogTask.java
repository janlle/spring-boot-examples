package com.leone.boot.mvc.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leone.boot.common.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 模拟用户产生日志
 * 每隔指定毫秒生成日志   @Scheduled(fixedRate = 100)
 * 使用cron表达式       @Scheduled(cron = "0/1 * * * * ?")
 *
 * @author leone
 * @since 2018-06-12
 **/
//@Component
public class MainLogTask {

    private static final Logger JSON_LOG = LoggerFactory.getLogger("json-log");

    private static final Logger CSV_LOG = LoggerFactory.getLogger("csv-log");

    private static final Logger COMMON_LOG = LoggerFactory.getLogger("common-log");

    private static final Random RANDOM = new Random();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Long offset = 0L;

    private static LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * 18332562075,15032293356,2017/09/11 01:01:08,541
     * 打电话日志
     */
    @Async
    //@Scheduled(fixedRate = 10)
    public void csvLogTask() {
        //CSV_LOG.info(offset + "," + RandomValue.randomUsername() + "," + RandomValue.randomTel() + "," + RandomValue.randomInt(70) + "," + RandomValue.randomInt(10000) + "," + RandomValue.randomBirth());
        offset++;
        //CSV_LOG.info(System.currentTimeMillis() + "," + RandomValue.randomUsername() + "," + RandomValue.RANDOM.nextInt(60) + "," + RandomValue.randomTime() + "," + RandomValue.randomUUID().substring(15) + "," + RandomValue.randomWords() + "," + RANDOM.nextBoolean());
    }

    /**
     * 产生json日志任务
     */
    @Async
    //@Scheduled(fixedDelay = 10)
    public void jsonLogTask() throws JsonProcessingException {
        JSON_LOG.info(objectMapper.writeValueAsString(RandomUtils.randomUser()));
    }

    /**
     * 基本日志task
     */
    @Async
    //@Scheduled(fixedDelay = 5)
    public void commonLogTask() {
        //COMMON_LOG.info(RandomValue.randomWords() + " " + RandomValue.randomWords() + " " + RandomValue.randomWords() + " " + RandomValue.randomWords());
        //COMMON_LOG.info("," + RandomValue.randomUsername() + "," + RandomValue.randomInt(80) + "," + RandomValue.randomDouble(100));
    }

    /**
     * 访问日志
     */
    @Async
    //@Scheduled(fixedRate = 20)
    public void accessLogTask() {
        COMMON_LOG.info(System.currentTimeMillis() + "\t" + RandomUtils.randomMac() + "\t" + RandomUtils.randomTel() + "\t" + RandomUtils.randomUrl() + "\t" + RandomUtils.randomDriver() + "\t" + RandomUtils.randomIp() + "\t" + RANDOM.nextInt(100) + "\t" + RANDOM.nextInt(5000));
    }


}

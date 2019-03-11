package com.andy.log.task;

import com.andy.log.util.RandomValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * 模拟用户产生日志
 * 每隔指定毫秒生成日志   @Scheduled(fixedRate = 100)
 * 使用cron表达式       @Scheduled(cron = "0/1 * * * * ?")
 *
 * @author Leone
 * @since 2018-06-12
 **/
@Component
public class LogTask {

    private static final Logger JSON_LOG = LoggerFactory.getLogger("json-log");

    private static final Logger CSV_LOG = LoggerFactory.getLogger("csv-log");

    private static final Logger COMMON_LOG = LoggerFactory.getLogger("common-log");

    private static final Random RANDOM = new Random();

    private static final Date date = new Date();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * 18332562075,15032293356,2017/09/11 01:01:08,541
     * 打电话日志
     */
    @Async
    @Scheduled(fixedRate = 10)
    public void csvLog() {
//        CSV_LOG.info(System.currentTimeMillis() + "," + RandomValue.randomTel() + "," + RandomValue.randomTime() + "," + RANDOM.nextInt(1000));
        CSV_LOG.info(System.currentTimeMillis() + "," + RandomValue.randomUsername() + "," + RandomValue.random.nextInt(60) + "," + RandomValue.randomTime() + "," + RandomValue.randomUUID().substring(15) + "," + RandomValue.randomMessage() + "," + RANDOM.nextBoolean());
    }

    /**
     * 用户画像
     *
     * @Scheduled(fixedDelay = 50)
     */
    @Async
//    @Scheduled(fixedDelay = 30)
    public void commonLog() {
        COMMON_LOG.info(RandomValue.randomTime() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.randomUsername() + "\t" + RandomValue.randomIp() + "\t" + RandomValue.randomIDCard() + "\t" + RandomValue.randomDriver() + "\t" + RandomValue.randomUserAgent());
    }

    /**
     * @Scheduled(fixedRate = 10)
     * 产生随机单词
     */
    @Async
    public void randomWord() {
        COMMON_LOG.info(RandomValue.randomMessage() + " " + RandomValue.randomMessage());
    }

    /**
     * 用户访问ip产生的日志(每3秒执行一次)
     */
    @Async
//    @Scheduled(fixedRate = 20)
    public void userVisitLogTask() {
        COMMON_LOG.info(System.currentTimeMillis() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.randomTel() + "\t" + RandomValue.randomUrl() + "\t" + RandomValue.randomDriver() + "\t" + RandomValue.randomIp() + "\t" + RANDOM.nextInt(100) + "\t" + RANDOM.nextInt(5000));
    }

    /**
     * @Scheduled(cron = "0/1 * * * * ?")
     * 产生json日志任务
     */
    @Async
//    @Scheduled(fixedDelay = 30)
    public void jsonLog() throws JsonProcessingException {
        JSON_LOG.info(objectMapper.writeValueAsString(RandomValue.randomUser()));
    }

}

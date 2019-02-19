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

    private static final Logger logger = LoggerFactory.getLogger(LogTask.class);

    private static final Logger CALL_LOG = LoggerFactory.getLogger("call-log");

    private static final Logger USER_INFO = LoggerFactory.getLogger("user-info");

    private static final Random RANDOM = new Random();

    private static final Date date = new Date();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * 18332562075,15032293356,2017/09/11 01:01:08,541
     *
     *
     * 用户打电话日志
     */
    @Async
    @Scheduled(fixedRate = 20)
    public void callLogTask() {
        CALL_LOG.info(System.currentTimeMillis() + "," + RandomValue.randomTel() + "," + RandomValue.randomTime() + "," + RANDOM.nextInt(1000));
    }

    /**
     * @Scheduled(fixedDelay = 50)
     * 用户聊天产生的日志()
     */
    @Async
    @Scheduled(fixedDelay = 50)
    public void userChatLogTask() {
        logger.info(System.currentTimeMillis() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.randomUsername() + "\t" + RandomValue.getIp() + "\t" + RandomValue.randomUsername() + "\t" + RandomValue.getIp() + "\t" + RandomValue.getMessage() + "\t" + LocalDateTime.now().toLocalTime());
    }

    /**
     * @Scheduled(fixedRate = 10)
     * 产生随机单词
     */
    @Async
    public void randomWord() {
        logger.info(RandomValue.getMessage() + " " + RandomValue.getMessage());
    }

    /**
     * 用户访问ip产生的日志(每3秒执行一次)
     */
    @Async
//    @Scheduled(fixedRate = 20)
    public void userVisitLogTask() {
        logger.info(System.currentTimeMillis() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.randomTel() + "\t" + RandomValue.getUrl() + "\t" + RandomValue.randomDriver() + "\t" + RandomValue.getIp() + "\t" + RANDOM.nextInt(100) + "\t" + RANDOM.nextInt(5000));
    }

    /**
     * @Scheduled(cron = "0/1 * * * * ?")
     * 产生json日志任务
     */
    @Async
    @Scheduled(fixedDelay = 20)
    public void jsonLogTask() throws JsonProcessingException {
        USER_INFO.info(objectMapper.writeValueAsString(RandomValue.getUser()));
    }

}

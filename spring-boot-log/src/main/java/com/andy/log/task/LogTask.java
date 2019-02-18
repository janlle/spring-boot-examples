package com.andy.log.task;

import com.andy.log.util.RandomValue;
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

    private static final Random RANDOM = new Random();

    private static final Date date = new Date();

    private static LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * 18332562075,15032293356,2017/09/11 01:01:08,541
     *
     * @Scheduled(fixedRate = 50)
     * 用户打电话日志
     */
    @Async
    public void callLogTask() {
        logger.info(System.currentTimeMillis() + "," + RandomValue.randomTel() + "," + RandomValue.randomTime() + "," + RANDOM.nextInt(1000));
    }

    /**
     * @Scheduled(fixedDelay = 50)
     * 用户聊天产生的日志()
     */
    @Async
    public void userChatLogTask() {
        logger.info(System.currentTimeMillis() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.getName() + "\t" + RandomValue.getIp() + "\t" + RandomValue.getName() + "\t" + RandomValue.getIp() + "\t" + RandomValue.getMessage() + "\t" + LocalDateTime.now().toLocalTime());
    }

    @Async
    // @Scheduled(fixedRate = 10)
    public void randomWord() {
        logger.info(RandomValue.getMessage() + " " + RandomValue.getMessage());
    }

    /**
     *
     * 用户访问ip产生的日志(每3秒执行一次)
     */
    @Async
    @Scheduled(fixedRate = 20)
    public void userVisitLogTask() {
        logger.info(System.currentTimeMillis() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.randomTel() + "\t" + RandomValue.getUrl() + "\t" + RandomValue.randomDriver() + "\t" + RandomValue.getIp() + "\t" + RANDOM.nextInt(100) + "\t" + RANDOM.nextInt(5000));
    }

    /**
     * @Scheduled(cron = "0/1 * * * * ?")
     * 用户发送邮件产生的日志(每分钟执行一次)
     */
    @Async
    public void userSendMailLogTask() {
        logger.info(System.currentTimeMillis() + "\t" + RandomValue.randomMac() + "\t" + RandomValue.getEmail() + "\t" + RandomValue.getEmail() + "\t" + RandomValue.getMessage() + "\t" + LocalDateTime.now().toLocalTime());
    }

    /**
     * @Scheduled(cron = "0 0/2 * * * ?")
     * 用户秒杀商品产生的日志(每2分钟执行一次)
     */
    public void secondKillLogTask() {
        for (int i = 0; i < RandomValue.random.nextInt(156) + 100; i++) {
            logger.info(System.currentTimeMillis() + "\t" + RandomValue.getName() + "\tsecKill\t" + RandomValue.getGoods() + "\tphone\t" + RandomValue.randomTel() + "\ttime\t" + LocalDateTime.now().toLocalTime());
        }
    }


}

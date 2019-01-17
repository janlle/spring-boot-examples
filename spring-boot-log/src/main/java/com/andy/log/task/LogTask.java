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
     * 用户购物产生的日志()
     */
    @Async
    @Scheduled(fixedRate = 100)
    public void orderLogTask() {
        logger.info(System.currentTimeMillis() + "\tip\t" + RandomValue.getIp() + "\tusername\t" + RandomValue.getName() + "\tgoodsName\t" + RandomValue.getGoods() + "\torderId\t" + RandomValue.getNum(24));
    }

    /**
     * 用户聊天产生的日志()
     */
    @Async
    @Scheduled(fixedDelay = 50 * 2)
    public void userChatLogTask() {
        logger.info(System.currentTimeMillis() + "\tfromUser\t" + RandomValue.getName() + "\tip\t" + RandomValue.getIp() + "\ttoUser\t" + RandomValue.getName() + "\tip\t" + RandomValue.getIp() + "\tmessage\t" + RandomValue.getMessage() + "\ttime\t" + LocalDateTime.now().toLocalTime());
    }

    /**
     * 用户访问ip产生的日志(每3秒执行一次)
     */
    @Async
    @Scheduled(cron = "0/1 * * * * ?")
    public void userVisitLogTask() {
        logger.info(System.currentTimeMillis() + "\tphone\t" + RandomValue.getTel() + "\turl\t" + RandomValue.getUrl() + "\tip\t" + RandomValue.getIp() + "\ttime\t" + RANDOM.nextInt(20));
    }

    /**
     * 用户发送邮件产生的日志(每分钟执行一次)
     */
    @Async
    @Scheduled(cron = "0/1 * * * * ?")
    public void userSendMailLogTask() {
        logger.info(System.currentTimeMillis() + "\tfromMail\t" + RandomValue.getEmail() + "\ttoMail\t" + RandomValue.getEmail() + "\tcontent\t" + RandomValue.getMessage() + "\ttime\t" + LocalDateTime.now().toLocalTime());
    }

    /**
     * 用户秒杀商品产生的日志(每2分钟执行一次)
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void secondKillLogTask() {
        for (int i = 0; i < RandomValue.random.nextInt(156) + 100; i++) {
            logger.info(System.currentTimeMillis() + "\t" + RandomValue.getName() + "\tsecKill\t" + RandomValue.getGoods() + "\tphone\t" + RandomValue.getTel() + "\ttime\t" + LocalDateTime.now().toLocalTime());
        }
    }


}

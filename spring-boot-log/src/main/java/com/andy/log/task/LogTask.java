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
 * @author: lyon
 * @since: 2018-06-12 09:12
 **/
@Component
public class LogTask {

    private static final Logger logger = LoggerFactory.getLogger(LogTask.class);

    private static String goods[] = {"iphone", "笔记本", "苹果", "橘子", "橙子", "矿泉水","手机", "书包"};

    private static final Random RANDOM = new Random();

    private static LocalDateTime localDateTime = LocalDateTime.now();

    @Async
    @Scheduled(fixedRate = 1000 * 2)
    public void timeTask() {
        logger.info(localDateTime.toLocalDate()+ RandomValue.getName()+"-买了-"+goods[RANDOM.nextInt(goods.length)]+"-订单号是-"+System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedDelay = 1000 * 5)
    public void DelayTask() {
        logger.info(localDateTime.toLocalDate()+RandomValue.getName()+"-购买的-"+goods[RANDOM.nextInt(goods.length)]+"-发货了！-"+System.currentTimeMillis());
    }

    //每5秒执行一次
    @Async
    @Scheduled(cron = "0/3 * * * * ?")
    public void doSomething() {
        logger.info(new Date().getTime()+"-"+RandomValue.getTel()+"-访问了-"+RandomValue.getIp()+"-用了"+RANDOM.nextInt(500) + "-秒");
    }

    // 每分钟执行一次
    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void work() {
        logger.info(new Date().getTime()+"-"+RandomValue.getName()+"-登陆了-"+RandomValue.getEmail(5,8)+"-邮箱，发送了-"+RANDOM.nextInt(4) + "条邮件！");
    }


    // 每8分钟执行一次
    @Scheduled(cron = "0 0/8 * * * ?")
    public void works() {
        for (int i = 0; i < 256; i++) {
            logger.info(new Date().getTime()+"-"+RandomValue.getName()+"-登陆了-"+RandomValue.getEmail(5,8)+"-邮箱，发送了-"+RANDOM.nextInt(4) + "条邮件！");
        }
    }


}

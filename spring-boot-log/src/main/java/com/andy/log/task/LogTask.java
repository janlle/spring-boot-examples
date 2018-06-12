package com.andy.log.task;

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
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-12 09:12
 **/
@Component
public class LogTask {

    private static final Logger logger = LoggerFactory.getLogger(LogTask.class);

    private static String names[] = {"张三", "李四", "王五", "赵六", "宋江", "老王","刘萨芬", "老赵", "李云龙", "林冲","武松"};

    private static String goods[] = {"iphone", "笔记本", "苹果", "橘子", "橙子", "矿泉水","手机", "书包"};

    private static final Random RANDOM = new Random();

    private static LocalDateTime localDateTime = LocalDateTime.now();

    @Async
    @Scheduled(fixedRate = 1000 * 2)
    public void timeTask() {
        logger.info(localDateTime.toLocalDate()+names[RANDOM.nextInt(names.length)]+"-买了-"+names[RANDOM.nextInt(goods.length)]+"-订单号是-"+System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedDelay = 1000 * 5)
    public void DelayTask() {
        logger.info(new Date().getTime()+names[RANDOM.nextInt(names.length)]+"购买的"+names[RANDOM.nextInt(goods.length)]+"发货了！"+RANDOM.nextInt(5000)*10000);
    }



    //每5秒执行一次
    @Async
    @Scheduled(cron = "0/3 * * * * ?")
    public void doSomething() throws Exception {
        logger.info(new Date().getTime()+names[RANDOM.nextInt(names.length)]+"---买了----"+names[RANDOM.nextInt(goods.length)]+"----"+RANDOM.nextInt(5000)*10000);
    }


    // 每分钟执行一次
    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void work() throws Exception {
        logger.info(new Date().getTime()+names[RANDOM.nextInt(names.length)]+"---买了----"+names[RANDOM.nextInt(goods.length)]+"----"+RANDOM.nextInt(5000)*10000);
    }

    // 每一小时执行一次
    @Async
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void goWork() throws Exception {
        logger.info(new Date().getTime()+names[RANDOM.nextInt(names.length)]+"---买了----"+names[RANDOM.nextInt(goods.length)]+"----"+RANDOM.nextInt(5000)*10000);
    }


    public static void main(String[] args) {
        System.out.println(localDateTime.toLocalDate());
    }

}

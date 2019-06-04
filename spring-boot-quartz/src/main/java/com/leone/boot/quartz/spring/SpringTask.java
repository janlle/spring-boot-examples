package com.leone.boot.quartz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 固定等待时间 @Scheduled(fixedDelay = 时间间隔 )
 * 固定间隔时间 @Scheduled(fixedRate = 时间间隔 )
 * Corn表达式 @Scheduled(cron = Corn表达式)
 *
 * @author leone
 * @since 2018-05-27
 **/
@Slf4j
@Component
public class SpringTask {

    private static Random random = new Random();

    /**
     * fixedRate固定间隔时间执行一次任务，上一个任务就算提前执行完成也要等到规定的时间执行下一个任务，单位时间内执行的任务数基本固定
     */
    @Scheduled(fixedRate = 1000 * 2)
    public void fixedRate() {
        int i = random.nextInt(8);
        try {
            TimeUnit.SECONDS.sleep(i);
            log.info("sleep {} seconds...", i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * fixedDelay在上一个任务执行完成后间隔多少秒执行下次任务，如果是根据任务消耗的时长来决定一段时间内执行任务的数量的
     */
    @Scheduled(fixedDelay = 1000 * 4)
    public void delayTask() {
        int i = random.nextInt(8);
        try {
            TimeUnit.SECONDS.sleep(i);
            log.info("sleep {} seconds...", i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据cron表达式，每到执行的时间就会去判断上一个任务是否执行完成，如果没有执行完成就等到下一个周期去执行，单位时间执行的任务数一定等于小于总周期数
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void cron() {
        int i = random.nextInt(8);
        try {
            TimeUnit.SECONDS.sleep(i);
            log.info("sleep {} seconds...", i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

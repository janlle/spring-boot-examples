package com.andy.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lyon
 * @since: 2018-05-08 21:30
 **/
@Slf4j
public class DeadLock implements Runnable {

    public int flag = 1;

    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            synchronized (o2) {
                log.info("1");
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            synchronized (o1) {
                log.info("0");
            }
        }

    }

    public static void main(String[] args) {
        DeadLock l1 = new DeadLock();
        DeadLock l2 = new DeadLock();

        l1.flag = 1;
        l2.flag = 0;

        new Thread(l1).start();
        new Thread(l2).start();
    }

}

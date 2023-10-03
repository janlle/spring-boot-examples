package com.leone.boot.jvm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author leone
 * @since 2018-07-14
 **/
@Service
public class DataService {

    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    private Boolean flag = true;

    private Boolean threadFlag = true;

    public void newObjects(Integer count) throws Exception {
        for (int i = 0; i < count; i++) {
            if (flag) {
                Thread.sleep(200);
                byte[][] bytes = new byte[1024][1024];
                log.info("count: {} current: {}", count, i);
            }
            System.out.println(i);
        }
    }

    public void stopNewObject() {
        flag = false;
    }

    public void newThread(Integer count) throws InterruptedException {
        if (count == null) {
            while (threadFlag) {
                Thread.sleep(1000);
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } else {
            for (int i = 0; i < count; i++) {
                Thread.sleep(1000);
                if (threadFlag) {
                    int f = i;
                    new Thread(() -> {
                        System.out.println(Thread.currentThread().getName() + f);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        }
    }

    public void stopNewThread() {
        flag = false;
    }


}

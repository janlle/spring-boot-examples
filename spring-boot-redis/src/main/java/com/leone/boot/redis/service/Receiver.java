package com.leone.boot.redis.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Receiver {

    public void receiveMessage(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Received <" + message + ">");
    }

}
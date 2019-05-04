package com.leone.design.creation.factorymethod;

/**
 * @author leone
 * @cerateBy: 2018-07-28
 **/
public class SmsSender implements Sender {
    @Override
    public void send(String message) {
        System.out.println("this is sms sender:" + message);
    }
}

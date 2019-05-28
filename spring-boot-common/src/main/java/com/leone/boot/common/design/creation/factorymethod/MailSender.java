package com.leone.boot.common.design.creation.factorymethod;

/**
 * @author leone
 * @since 2018-07-28
 **/
public class MailSender implements Sender {
    @Override
    public void send(String message) {
        System.out.println("this is mail sender:" + message);
    }
}

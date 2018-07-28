package com.andy.design.creation.builder;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class MailSender implements Sender {
    @Override
    public void send(String message) {
        System.out.println("this is mail sender:" + message);
    }
}

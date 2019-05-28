package com.leone.boot.common.design.creation.abstractfactory;

/**
 * @author leone
 * @since 2018-07-28
 **/
public class FactoryTest {

    public static void main(String[] args) {

        MailSenderFactory mailSenderFactory = new MailSenderFactory();
        Sender mailSender = mailSenderFactory.provider();
        mailSender.send("hello a");


        SmsSenderFactory smsSenderFactory = new SmsSenderFactory();
        Sender smsSender = smsSenderFactory.provider();
        smsSender.send("hello b");

    }

}

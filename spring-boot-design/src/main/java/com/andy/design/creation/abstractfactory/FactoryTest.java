package com.andy.design.creation.abstractfactory;

/**
 * @author Leone
 * @cerateBy: 2018-07-28
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

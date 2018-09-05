package com.andy.design.creation.factorymethod;

/**
 * @author Leone
 * @cerateBy: 2018-07-28
 **/
public class FactoryTest {

    public static void main(String[] args) {
        SenderFactory factory = new SenderFactory();

        // 普通工厂方法
        Sender sender = factory.produce("sms");
        sender.send("hello");

        // 多个工厂方法模式
        Sender mail = factory.produceMail();
        mail.send("mail");

        // 静态工厂模式
        SenderFactory.staticProduceSms().send("hello world");

    }

}

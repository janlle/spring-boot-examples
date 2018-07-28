package com.andy.design.creation.factorymethod;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class FactoryTest {

    public static void main(String[] args) {

        // 普通工厂方法
        SenderFactory factory = new SenderFactory();
        Sender sender = factory.produce("sms");
        sender.send("hello");

        // 多个工厂方法模式
        FactoryTest factoryTest = new FactoryTest();
        Sender mail = factoryTest.produceMail();
        mail.send("mail");

        // 静态工厂模式
        FactoryTest.staticProduceSms().send("hello world");

    }


    /**
     * 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象。
     */

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }


    /**
     * 静态工厂方法模式，将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可
     */

    public static Sender staticProduceMail() {
        return new MailSender();
    }

    public static Sender staticProduceSms() {
        return new SmsSender();
    }

}

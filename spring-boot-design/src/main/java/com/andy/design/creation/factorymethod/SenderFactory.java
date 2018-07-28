package com.andy.design.creation.factorymethod;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class SenderFactory {

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }

}

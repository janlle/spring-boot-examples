package com.andy.design.creation.abstractfactory;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class MailSenderFactory implements Provider {

    @Override
    public Sender provider() {
        return new MailSender();
    }
}

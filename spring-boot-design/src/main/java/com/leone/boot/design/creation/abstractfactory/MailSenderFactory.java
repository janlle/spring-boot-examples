package com.leone.boot.design.creation.abstractfactory;

/**
 * @author leone
 * @cerateBy: 2018-07-28
 **/
public class MailSenderFactory implements Provider {

    @Override
    public Sender provider() {
        return new MailSender();
    }
}

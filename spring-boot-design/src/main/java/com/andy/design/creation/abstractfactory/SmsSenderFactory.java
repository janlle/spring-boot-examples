package com.andy.design.creation.abstractfactory;

/**
 * @author: Leone
 * @cerateBy: 2018-07-28
 **/
public class SmsSenderFactory implements Provider {

    @Override
    public Sender provider() {
        return new SmsSender();
    }
}

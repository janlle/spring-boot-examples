package com.andy.design.creation.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class Builder {

    private List<Sender> list = new ArrayList<>();

    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }

}

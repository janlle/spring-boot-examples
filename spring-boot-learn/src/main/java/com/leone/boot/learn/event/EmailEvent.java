package com.leone.boot.learn.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-21
 **/
public class EmailEvent extends ApplicationEvent {

    public EmailEvent(EmailInfo source) {
        super(source);
    }

}

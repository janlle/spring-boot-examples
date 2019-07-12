package com.leone.boot.learn.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * <p>
 *
 * @author leone
 * @since 2019-07-04
 **/
public class EmailEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publisherEvent(EmailEvent emailEvent) {
        applicationEventPublisher.publishEvent(emailEvent);
    }

}

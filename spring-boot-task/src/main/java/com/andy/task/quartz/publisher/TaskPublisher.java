package com.andy.task.quartz.publisher;

import com.andy.task.quartz.event.TaskEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
@Slf4j
public class TaskPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(final String message) {
        log.info("Publishing custom event. ");
        TaskEvent taskPublisher = new TaskEvent(this, message);
        applicationEventPublisher.publishEvent(taskPublisher);
    }

}

package com.andy.task.quartz.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
public class TaskEvent extends ApplicationEvent {

    private String message;

    public TaskEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
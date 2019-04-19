package com.leone.quartz.listener;

import com.leone.quartz.event.TaskEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
public class TaskEventListener implements ApplicationListener<TaskEvent> {

    @Override
    public void onApplicationEvent(TaskEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }

}

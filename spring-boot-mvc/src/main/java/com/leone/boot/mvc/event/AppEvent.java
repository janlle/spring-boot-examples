package com.leone.boot.mvc.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author leone
 * @since 2018-04-10
 **/

public class AppEvent extends ApplicationEvent {

    private String msg ;

    public AppEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }


    public AppEvent(Object source) {
        super(source);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

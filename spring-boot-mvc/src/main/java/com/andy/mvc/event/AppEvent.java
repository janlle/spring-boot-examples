package com.andy.mvc.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-10 14:35
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

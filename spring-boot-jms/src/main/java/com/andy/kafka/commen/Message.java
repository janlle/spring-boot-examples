package com.andy.kafka.commen;

import lombok.ToString;

import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@ToString
public class Message<T> {

    private Long id;

    private T message;

    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
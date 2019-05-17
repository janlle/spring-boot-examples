package com.leone.boot.kafka.commen;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
public class Message<T> implements Serializable {

    private Long id;

    private T message;

    private Date time;

    public Message() {
    }

    public Message(Long id, T message, Date time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }

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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message=" + message +
                ", time=" + time +
                '}';
    }
}
package com.leone.boot.common;

import com.leone.boot.common.exception.ExceptionMessage;

import java.io.Serializable;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-24
 **/
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1813924894386442775L;

    private String status;

    private String messages;

    private Integer code;

    private T data;

    private Result() {
    }

    private Result(String status, String messages, Integer code, T data) {
        this.status = status;
        this.messages = messages;
        this.code = code;
        this.data = data;
    }

    private Result(ExceptionMessage exceptionMessage, String status) {
        this.status = status;
        this.messages = exceptionMessage.getMessage();
        this.code = exceptionMessage.getCode();
        this.data = null;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static <T> Result<T> build(String status, String message, Integer errorCode, T data) {
        return new Result<>(status, message, errorCode, data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>("error", message, 0, null);
    }

    public static <T> Result<T> error(ExceptionMessage exceptionMessage) {
        return new Result<>(exceptionMessage, "error");
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("success", "", 0, data);
    }


}

package com.leone.boot.common;

import com.leone.boot.common.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-24
 **/
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1813924894386442775L;

    private Integer code;

    private String messages;

    private T data;

    private Result() {
    }

    private Result(String messages, Integer code, T data) {
        this.messages = messages;
        this.code = code;
        this.data = data;
    }

    private Result(ExceptionEnum exceptionEnum) {
        this.messages = exceptionEnum.getMessage();
        this.code = exceptionEnum.getCode();
        this.data = null;
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

    public static <T> Result<T> build(String message, Integer errorCode, T data) {
        return new Result<>(message, errorCode, data);
    }

    public static <T> Result<T> error(ExceptionEnum exceptionEnum) {
        return new Result<>(exceptionEnum);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("success", 20000, data);
    }


}

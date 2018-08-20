package com.andy.jwt.common;

import java.io.Serializable;
/**
 * @author: Leone
 * @since: 2018-04-19 20:19
 **/
public class BaseResponse<T> implements Serializable {

    private String msg;

    private Integer code;

    private T data;

    private BaseResponse() {}

    private BaseResponse(ResultEnum baseResult, T data) {
        this.code = baseResult.getCode();
        this.msg = baseResult.getMsg();
        this.data = data;
    }

    private BaseResponse(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse(ResultEnum.SUCCESS, data);
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse(ResultEnum.ERROR, data);
    }

    public static <T> BaseResponse<T> build(String mag, Integer code, T data) {
        return new BaseResponse(mag, code, data);
    }



    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}



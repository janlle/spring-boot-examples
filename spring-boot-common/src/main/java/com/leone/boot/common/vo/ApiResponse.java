package com.leone.boot.common.vo;

import java.util.List;

/**
 * @author leone
 * @since 2018-08-09
 **/
public class ApiResponse<T> {

    private Integer code;

    private String message;

    private List<T> data;

    public ApiResponse(Integer code) {
        this.code = code;
    }

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

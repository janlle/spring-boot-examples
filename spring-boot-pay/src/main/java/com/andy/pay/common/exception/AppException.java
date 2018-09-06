package com.andy.pay.common.exception;

import com.andy.pay.common.enums.ResultEnum;

/**
 * @author Leone
 * @since 2018-05-08
 **/
public class AppException extends RuntimeException {

    private String message;

    private Integer code;

    public AppException(Integer code, String message) {
        super(message);
    }

    public AppException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

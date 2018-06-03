package com.andy.pay.enums;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-06-03
 **/
public enum ResultEnum {

    SUCCESS(20000, "success"),
    ERROR(50000, "error"),
    USER_NOT_FOUND(40001, "用户不存在"),
    USER_NAME_EXIST(40002, "用户名存在"),
    USERNAME_PASSWORD_FAIL(40003, "用户名或密码错误"),
    USER_TOKEN_VALIDATE_FAIL(40004, "用户token校验失败"),
    USER_ID_TOKEN_NOT_MATCHING(40005, "用户id和token不匹配"),
    USER_ACCOUNT_STYLE_FAIL(40006, "账号格式有误"),
    USER_PASSWORD_IS_EMPTY(40007, "用户密码为空"),
    USER_PASSWORD_LENGTH_FAIL(40008, "用户密码长度必须为6-16位"),
    CODE_MISMATCHING(40009, "验证码不匹配"),
    VALIDATE_CODE_EXPIRED(40010, "验证已码过期"),
    GET_VALIDATE_CODE_ERROR(50010, "获取短信验证码失败"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

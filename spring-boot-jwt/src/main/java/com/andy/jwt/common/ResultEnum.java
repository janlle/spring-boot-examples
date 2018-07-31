package com.andy.jwt.common;

/**
 * @author: Mr.ruoLin
 * @since: 2018-04-19 20:31
 **/
public enum ResultEnum {
    SUCCESS("success！", 10000),
    WARNING("warning！", 20000),
    ERROR("error！", 30000),
    ;

    private String msg;

    private Integer code;

    ResultEnum() {}

    ResultEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }


    public ResultEnum get(ResultEnum target){
        for (ResultEnum baseResult : ResultEnum.values()) {
            if (baseResult == target) {
                return baseResult;
            }
        }
        return null;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

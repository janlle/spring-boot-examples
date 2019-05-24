package com.leone.boot.mvc.sign;

import java.io.Serializable;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-24
 **/
public class SignatureBean<T> implements Serializable {

    private T data;

    private String appId;

    private String nonceStr;

    private String sign;

    private Long timestamp;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

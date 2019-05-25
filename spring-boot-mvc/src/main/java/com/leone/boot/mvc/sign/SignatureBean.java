package com.leone.boot.mvc.sign;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sign;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String secret;

    private Long timestamp;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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

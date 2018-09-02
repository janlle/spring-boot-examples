package com.andy.mvc.utils.qiniu;

/**
 * <p>
 *
 * @author: Leone
 **/
public class QiNiuToken {

    private String token;

    public QiNiuToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

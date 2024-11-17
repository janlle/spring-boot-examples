package com.leone.boot.dubbo.api.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2017-11-09
 **/
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -8654229598582165411L;

    private Long userId;

    private String account;

    private Date createTime;

    public User() {
    }

    public User(Long userId, String account, Date createTime) {
        this.userId = userId;
        this.account = account;
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
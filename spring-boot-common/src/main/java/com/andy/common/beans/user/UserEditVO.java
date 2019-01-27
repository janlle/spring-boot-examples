package com.andy.common.beans.user;

import java.io.Serializable;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-09
 **/
public class UserEditVO implements Serializable {

    private Long userId;

    private String account;

    private String password;

    private String description;

    private Integer age;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

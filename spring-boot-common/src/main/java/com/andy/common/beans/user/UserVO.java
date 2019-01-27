package com.andy.common.beans.user;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-09
 **/
public class UserVO implements Serializable {

    private Long userId;

    private String account;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

package com.andy.aop.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leone
 * @since 2018-06-21
 **/
public class User implements Serializable {

    private Long userId;

    private String account;

    private String password;

    private Date birthday;

    private Double salary;

    private Date createTime;

    private Boolean deleted;

    public User() {
    }

    public User(Long userId, String account, String password, Date birthday, Double salary, Date createTime, Boolean deleted) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.birthday = birthday;
        this.salary = salary;
        this.createTime = createTime;
        this.deleted = deleted;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                '}';
    }
}

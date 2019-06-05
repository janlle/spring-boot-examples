package com.leone.boot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2017-11-09
 **/
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -8654229598582165411L;

    @TableId(type = IdType.AUTO)
    private Long userId;

    @TableField
    private String account;

    @TableField
    private String password;

    @TableField
    private String description;

    @TableField
    private Integer age;

    @TableField
    private Date createTime;

    @TableLogic
    private boolean deleted;

    public User() {
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public User(Long userId, String account, String password, String description, Integer age, Date createTime, Boolean deleted) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.description = description;
        this.age = age;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                '}';
    }
}
package com.andy.jms.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

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
}

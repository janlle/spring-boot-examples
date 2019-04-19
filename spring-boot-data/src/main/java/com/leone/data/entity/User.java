package com.leone.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p> 
 *
 * @author Leone
 * @since 2017-11-09
 **/
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 2083333064832472496L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition = "varchar(128) not null comment '账号'")
    private String account;

    @Column(columnDefinition = "varchar(128) not null comment '密码'")
    private String password;

    @Column(columnDefinition = "text comment '自我介绍'")
    private String description;

    @Column(columnDefinition = "int(11) not null comment '年龄'")
    private Integer age;

    @Column(columnDefinition = "timestamp not null default current_timestamp comment '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "bit not null comment '是否删除'")
    private boolean deleted;

    public User() {
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
}
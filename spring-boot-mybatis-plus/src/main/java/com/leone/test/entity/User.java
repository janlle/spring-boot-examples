package com.leone.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author leone
 * @since 2020-05-29
 */
@TableName("t_user")
public class User implements Serializable {

private static final long serialVersionUID=1L;

    private Long userId;

    private String account;

    private String password;

    private String description;

    private Integer age;

    private LocalDateTime createTime;

    private Integer deleted;


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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", account=" + account +
        ", password=" + password +
        ", description=" + description +
        ", age=" + age +
        ", createTime=" + createTime +
        ", deleted=" + deleted +
        "}";
    }
}

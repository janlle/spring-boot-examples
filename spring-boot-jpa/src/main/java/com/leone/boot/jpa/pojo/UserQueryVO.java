package com.leone.boot.jpa.pojo;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-23
 **/
public class UserQueryVO {

    private Long userId;

    private String account;

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

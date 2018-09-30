package com.andy.aop.reflect;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leone
 * @since 2018-06-21
 **/
public class Student implements Serializable {

    Long userId;

    private String account;

    public int sex;

    protected Date birthday;

    public Student() {
    }

    public Student(Long userId, String account, int sex, Date birthday) {
        this.userId = userId;
        this.account = account;
        this.sex = sex;
        this.birthday = birthday;
    }

    String defMethod() {
        return "default method";
    }

    private Long priMethod() {
        return 1L;
    }

    protected Date proMethod() {
        return new Date();
    }

    public int pubMethod() {
        return 3;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                '}';
    }
}

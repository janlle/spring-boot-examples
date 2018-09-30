package com.andy.aop.reflect;

import com.andy.aop.anno.NameAnnotation;

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
        System.out.println("无参构造方法");
    }

    public Student(Long userId, String account, int sex, Date birthday) {
        System.out.println("四个参数构造方法");
        this.userId = userId;
        this.account = account;
        this.sex = sex;
        this.birthday = birthday;
    }

    Student(Long userId, String account, int sex) {
        System.out.println("三个参数构造方法");
        this.userId = userId;
        this.account = account;
        this.sex = sex;
    }

    private Student(Long userId, String account) {
        System.out.println("二个参数构造方法");
        this.userId = userId;
        this.account = account;
    }

    protected Student(Long userId) {
        System.out.println("一个参数构造方法");
        this.userId = userId;
    }

    String defMethod(String message) {
        return "default method " + message;
    }

    @NameAnnotation(name = "jack", age = 23)
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

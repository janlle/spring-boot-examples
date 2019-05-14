package com.leone.boot.batch.entity;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-08
 **/
public class Person {

    @Size(max = 10, min = 2)
    private String name;

    private Integer age;

    private String sex;

    private String address;

    private Date birthday;

    public Person() {
    }

    public Person(String name, Integer age, String sex, String address, Date birthday) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

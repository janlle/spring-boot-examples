package com.andy.design.creation.builder;

import java.util.Date;

/**构建者模式
 *
 * @author: Leone
 * @cerateBy: 2018-07-29
 **/
public class User {

    private String name;

    private Integer age;

    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

package com.andy.design.creation.builder;

import java.util.Date;

/**
 * @author: lyon
 * @cerateBy: 2018-07-29
 **/
public class Builder implements IBuilder {

    User user;

    public Builder() {
        user = new User();
    }

    @Override
    public void buildName() {
        user.setName("james");
    }

    @Override
    public void buildAge() {
        user.setAge(23);
    }

    @Override
    public void buildBirthday() {
        user.setBirthday(new Date());
    }

    @Override
    public User build() {
        return this.user;
    }
}

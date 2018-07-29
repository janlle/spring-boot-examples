package com.andy.design.creation.builder;

/**
 * @author: lyon
 * @cerateBy: 2018-07-29
 **/
public class UserDirector {
    public User constructUser(IBuilder builder) {
        builder.buildName();
        builder.buildAge();
        builder.buildBirthday();
        return builder.build();
    }
}
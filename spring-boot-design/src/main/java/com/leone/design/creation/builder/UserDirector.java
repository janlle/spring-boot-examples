package com.leone.design.creation.builder;

/**
 * @author leone
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
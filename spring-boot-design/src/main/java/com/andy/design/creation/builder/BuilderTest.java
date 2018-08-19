package com.andy.design.creation.builder;

/**
 * @author: Leone
 * @cerateBy: 2018-07-29
 **/
public class BuilderTest {

    public static void main(String[] args) {
        UserDirector director = new UserDirector();
        User user = director.constructUser(new Builder());

        System.out.println(user.getAge());
        System.out.println(user.getName());
        System.out.println(user.getBirthday());
    }


}

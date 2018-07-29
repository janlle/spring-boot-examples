package com.andy.design.creation.prototype;

import java.util.Date;

/**
 * @author: lyon
 * @cerateBy: 2018-07-28
 **/
public class PrototypeTest {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setAge(12);
        user.setBirthday(new Date());
        user.setName("james");
        user.setSchool(new School());

        System.out.println(user.hashCode());
        System.out.println(user.toString());
        System.out.println(user.getSchool().hashCode());
        System.out.println("----------------------------");

        User u1 = user.deepClone();
        System.out.println(u1.hashCode());
        System.out.println(u1.toString());
        System.out.println(u1.getSchool().hashCode());

    }

}


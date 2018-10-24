package com.andy.aop.clone;


import com.andy.aop.entity.User;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-15
 **/
public class CloneMain {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setUserId(12L);
        user.setAccount("hello");

        User u1 = user;

        System.out.println(user.hashCode());

        System.out.println(u1.hashCode());

        User u2 =  user.clone();

        System.out.println(u2.hashCode());

        System.out.println(user.getAccount() == u2.getAccount());

    }

}

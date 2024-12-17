package com.leone.boot.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * <p>
 *
 * @author leone
 * @since 2018-06-11
 **/
public class UserHelper {

    private static final Subject subject = SecurityUtils.getSubject();

    private UserHelper() {
    }

    public static String userId() {
        final Object userId = subject.getPrincipal();
        if (userId == null) {
            throw new AuthenticationException();
        }
        return userId.toString();
    }

    public static boolean isLogin() {
        final Object userId = subject.getPrincipal();
        return userId != null;
    }

}

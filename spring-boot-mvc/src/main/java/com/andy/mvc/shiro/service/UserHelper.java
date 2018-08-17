package com.andy.mvc.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * @author: lyon
 * @since: 2018-08-13
 **/
public class UserHelper {

    private static final Subject subject = SecurityUtils.getSubject();

    public static Integer getId() {
        Object id = subject.getPrincipal();
        if (id == null) {
            throw new AuthenticationException();
        }
        return Integer.parseInt(id.toString());
    }

}

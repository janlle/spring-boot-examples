package com.leone.mvc.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * <p>
 *
 * @author leone
 **/
public class UserHelper {

    /**
     * 获取用户id,必须通过token认证
     *
     * @return
     */
    public static Integer getUserId() {
        Subject subject = SecurityUtils.getSubject();
        Object userId = subject.getPrincipal();

        if (userId == null) {
            throw new AuthenticationException();
        }

        return Integer.parseInt(userId.toString());
    }

    /**
     * 判断用户是否登录,必须通过token认证
     *
     * @return
     */
    public static Boolean isLogin() {
        Subject subject = SecurityUtils.getSubject();

        Object userId = subject.getPrincipal();

        if (userId == null) {
            return false;
        }

        return true;
    }

}

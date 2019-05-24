package com.leone.boot.mvc.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import java.util.Objects;

/**
 * <p>
 *
 * @author leone
 **/
public abstract class UserHelper {

    public static final Subject subject = SecurityUtils.getSubject();

    /**
     * 获取用户id,必须通过token认证
     *
     * @return
     */
    public static Integer getUserId() {
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
        Object userId = subject.getPrincipal();
        return Objects.isNull(userId);
    }

}

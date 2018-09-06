package com.andy.shiro.common.util;

import com.andy.shiro.config.Token;
import com.andy.shiro.entity.rbac.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-06-11
 **/
@Slf4j
public class UserHelper {

    private static final Subject subject = SecurityUtils.getSubject();

    private UserHelper() {
    }

    public static Integer userId() {
        final Object userId = subject.getPrincipal();
        if (userId == null) {
            throw new AuthenticationException();
        }
        return Integer.parseInt(userId.toString());
    }

    public static boolean isLogin() {
        final Object userId = subject.getPrincipal();
        if (userId == null) {
            return false;
        }
        return true;
    }

    public static void login(Token token) {
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            log.info("user:{}", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.andy.shiro.test;

import com.andy.shiro.config.AuthRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author: lyon
 * @createBy: 2018-07-21 14:03
 **/
public class CustomerRealmTest {

    AuthRealm authRealm = new AuthRealm();

    @Test
    public void shiroAuthTest() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(authRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("james", "james");
        subject.login(token);

        System.out.println("is login:" + subject.isAuthenticated());

        subject.checkRoles("admin", "user");
        subject.checkPermission("user:delete");
        subject.checkPermission("user:update");
    }

}

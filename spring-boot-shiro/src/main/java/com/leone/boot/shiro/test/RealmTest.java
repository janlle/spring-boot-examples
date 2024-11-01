package com.leone.boot.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;


/**
 * @author leone
 * @since 2018-07-21
 **/

public class RealmTest {

    SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    public void init() {
        accountRealm.addAccount("james", "admin", "admin", "user");
    }

    
    public void shiroAuthTest() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(accountRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("james", "admin");
        subject.login(token);

        System.out.println("is login:" + subject.isAuthenticated());

        subject.checkRoles("admin", "user");
//        subject.logout();
//        System.out.println("is login:" + subject.isAuthenticated());
    }


}

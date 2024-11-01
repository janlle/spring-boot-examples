package com.leone.boot.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;


/**
 * @author leone
 * @since 2018-07-21
 **/

public class IniRealmTest {

    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    public void init() {
        accountRealm.addAccount("james", "admin", "admin", "user");
    }

    
    public void shiroAuthTest() {
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("james", "james");
        subject.login(token);

        System.out.println("is login:" + subject.isAuthenticated());


        subject.checkRoles("admin", "user");
        subject.checkPermission("user:delete");
        subject.checkPermission("user:update");
//        subject.logout();
//        System.out.println("is login:" + subject.isAuthenticated());


    }


}

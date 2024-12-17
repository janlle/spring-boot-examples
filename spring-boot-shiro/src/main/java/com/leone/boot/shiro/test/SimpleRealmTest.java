package com.leone.boot.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;


/**
 * @author leone
 * @since 2018-07-21
 **/
public class SimpleRealmTest {

    public static void main(String[] args) {
        SimpleAccountRealm accountRealm = new SimpleAccountRealm();
        accountRealm.addAccount("james", "admin", "admin", "user");

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(accountRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("james", "admin");
        subject.login(token);

        System.out.println("is login:" + subject.isAuthenticated());
        subject.checkRoles("admin", "user");
        subject.logout();
        System.out.println("is login:" + subject.isAuthenticated());

    }

    @Test
    public void randomTest() {
        //5d41402abc4b2a76b9719d911017c592
        //5d41402abc4b2a76b9719d911017c592
        //System.out.println(new Md5Hash("admin", "123"));
    }

    @Test
    public void encode() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用那种摘要算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1);
    }

}

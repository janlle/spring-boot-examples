package com.leone.boot.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author leone
 * @since 2018-07-21
 **/

public class JdbcRealmTest {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/boot");
        dataSource.setUsername("root");
        dataSource.setPassword("xx");
    }

    @Test
    public void shiroAuthTest() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);

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

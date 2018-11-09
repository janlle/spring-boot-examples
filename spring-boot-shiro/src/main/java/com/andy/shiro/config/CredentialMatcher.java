package com.andy.shiro.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.util.Objects;

/**
 * <p>匹配器
 *
 * @author Leone
 * @since 2018-04-21
 **/
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = usernamePasswordToken.getPassword().toString();
        String dbPassword = info.getCredentials().toString();
        return Objects.equals(password, dbPassword);
    }


}

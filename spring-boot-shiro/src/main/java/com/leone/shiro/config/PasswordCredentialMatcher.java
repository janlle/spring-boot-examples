package com.leone.shiro.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.util.Objects;

/**
 * <p>匹配器
 *
 * @author leone
 * @since 2018-04-21
 **/
public class PasswordCredentialMatcher extends SimpleCredentialsMatcher {

    /**
     * 自定义匹配逻辑
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = String.valueOf(usernamePasswordToken.getPassword());
        String dbPassword = info.getCredentials().toString();
        return Objects.equals(password, dbPassword);
    }


}

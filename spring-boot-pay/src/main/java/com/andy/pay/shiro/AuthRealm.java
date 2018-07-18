package com.andy.pay.shiro;

import com.andy.pay.shiro.config.ShiroProperty;
import com.andy.pay.shiro.filter.TokenFilter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class AuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private ShiroProperty shiroProperty;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> redis;


    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo...");
        Token token = (Token) authenticationToken;
        String tokenString = token.getToken();
        String userId = this.redis.get(this.shiroProperty.getPrefix() + "auth.token.id:" + tokenString);
        return !StringUtils.isEmpty(userId) ? new SimpleAuthenticationInfo(userId, tokenString, this.getName()) : null;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo...");
        String userId = (String) principals.getPrimaryPrincipal();
        if (!StringUtils.isEmpty(userId)) {
            String role = this.redis.get(this.shiroProperty.getPrefix() + "auth.id.role:" + userId);
            if (StringUtils.isEmpty(role)) {
                return null;
            } else {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.addRole(role);
                return info;
            }
        } else {
            return null;
        }
    }


}

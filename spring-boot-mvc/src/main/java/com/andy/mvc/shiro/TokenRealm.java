package com.andy.mvc.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
public class TokenRealm extends AuthorizingRealm {
    @Autowired
    private ShiroProperties shiroProperties;
    @Resource(
            name = "stringRedisTemplate"
    )
    private ValueOperations<String, String> redis;

    public TokenRealm() {
    }

    public String getName() {
        return "redisRealm";
    }

    public boolean supports(AuthenticationToken token) {
        return token != null && Token.class.isAssignableFrom(token.getClass());
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Token Token = (Token) authenticationToken;
        String tokenString = Token.getToken();
        String userId = (String) this.redis.get(this.shiroProperties.getPrefix() + "auth.token.id:" + tokenString);
        return !StringUtils.isEmpty(userId) ? new SimpleAuthenticationInfo(userId, tokenString, this.getName()) : null;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = (String) principals.getPrimaryPrincipal();
        if (!StringUtils.isEmpty(userId)) {
            String role = (String) this.redis.get(this.shiroProperties.getPrefix() + "auth.id.role:" + userId);
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

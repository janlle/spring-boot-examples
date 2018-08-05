package com.andy.pay.shiro;

import com.andy.pay.shiro.config.ShiroProperty;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author: lyon
 * @since: JDK8.0
 **/
@Component
public class AuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Resource
    private ShiroProperty shiroProperty;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo...");
        Token token = (Token) authenticationToken;
        String tokenString = token.getToken();
        tokenString = TokenUtil.decode(tokenString);
        if (StringUtils.isEmpty(tokenString)) {
            return null;
        }
        String userId = tokenString.split("\\.")[0];
        String dbToken = this.stringRedisTemplate.opsForValue().get(this.shiroProperty.getRedisPrefix() + "auth.token:" + userId);
        return !StringUtils.isEmpty(dbToken) ? new SimpleAuthenticationInfo(userId, tokenString, this.getName()) : null;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo...");
        String userId = (String) principals.getPrimaryPrincipal();
        if (!StringUtils.isEmpty(userId)) {
            String token = this.stringRedisTemplate.opsForValue().get(this.shiroProperty.getRedisPrefix() + "auth.token:" + userId);
            if (StringUtils.isEmpty(token)) {
                return null;
            } else {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                String role = TokenUtil.decode(token).split("\\.")[1];
                info.addRole(role);
                return info;
            }
        } else {
            return null;
        }
    }


}

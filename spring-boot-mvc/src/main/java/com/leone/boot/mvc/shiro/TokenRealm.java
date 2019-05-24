package com.leone.boot.mvc.shiro;

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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;


/**
 * @author leone
 **/
public class TokenRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(TokenRealm.class);

    @Resource
    private ShiroProperties shiroProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getName() {
        return "redisTokenRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && Token.class.isAssignableFrom(token.getClass());
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Token token = (Token) authenticationToken;
        String tokenString = token.getToken();
        String userIdTokenRole = TokenUtil.decode(tokenString, shiroProperties.getTokenSecret());
        String userId;
        try {
            userId = userIdTokenRole.split("\\.")[0];
        } catch (Exception e) {
            return null;
        }

        String redisToken = stringRedisTemplate.opsForValue().get(shiroProperties.getTokenPrefix() + userId);
        if (StringUtils.isEmpty(redisToken)) {
            return null;
        }
        logger.info("Authentication userId: {} -- token: {}", userId, tokenString);
        return new SimpleAuthenticationInfo(userId, tokenString, getName());
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = (String) principals.getPrimaryPrincipal();
        if (!StringUtils.isEmpty(userId)) {
            String tokenString = stringRedisTemplate.opsForValue().get(shiroProperties.getTokenPrefix() + userId);
            String userRole;
            try {
                String token = TokenUtil.decode(tokenString, shiroProperties.getTokenSecret());
                userRole = token.split("\\.")[1];
            } catch (Exception e) {
                return null;
            }
            logger.info("Authorization userId: {} -- role: {}", userId, userRole);
            return new SimpleAuthorizationInfo(Collections.singleton(userRole));
        }
        return null;
    }

}

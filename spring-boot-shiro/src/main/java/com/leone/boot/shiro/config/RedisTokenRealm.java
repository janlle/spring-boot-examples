package com.leone.boot.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author leone
 * @since 2020-05-26
 **/
@Slf4j
@Component
public class RedisTokenRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(RedisTokenRealm.class);

    @Resource
    private TokenProperties tokenProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String getName() {
        return "redisTokenRealm";
    }


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = (String) principalCollection.getPrimaryPrincipal();
        return null;
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
//        Token token = (Token) authenticationToken;
//        token.decode(tokenProperties.getCipherKey());
//        logger.info("Token: {}", token);
//
//        String redisToken = stringRedisTemplate.opsForValue().get(tokenProperties.getRedisPrefix() + tokenProperties.getTokenPrefix() + token.getUserId());
//        if (StringUtils.isEmpty(redisToken)) {
//            return null;
//        }
//
//        if (!StringUtils.isEmpty(token.getUserId())) {
//            return new SimpleAuthenticationInfo(token.getUserId(), token, getName());
//        }

        return null;
    }

}

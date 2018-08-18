package com.andy.mvc.shiro.base;

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
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Component
public class TokenRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(TokenRealm.class);

    @Resource
    private ShiroModuleProperties shiroModuleProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getName() {
        return "redisRealm";
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
        String userRole = TokenUtil.decode(tokenString);
        System.out.printf(tokenString);
        System.out.println(userRole);
        if (StringUtils.isEmpty(tokenString)) {
            return null;
        }
        String userId;
        try {
            userId = userRole.split("\\.")[0];
        } catch (Exception e) {
            return null;
        }
        logger.info("--------------userId:{}-----token:{}--------------", userId, tokenString);
        if (!StringUtils.isEmpty(userId)) {
            return new SimpleAuthenticationInfo(userId, tokenString, getName());
        }
        return null;
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
            String token = stringRedisTemplate.opsForValue().get(shiroModuleProperties.getTokenPrefix() + shiroModuleProperties.getTokenName() + userId);
            String userRole;
            try {
                userRole = token.split("\\.")[1];
            } catch (Exception e) {
                return null;
            }
            logger.info("--------------userId:{}-----role:{}--------------", userId, userRole);
            if (!StringUtils.isEmpty(userRole)) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.addRole(userRole);
                return info;
            }
        }
        return null;
    }

}

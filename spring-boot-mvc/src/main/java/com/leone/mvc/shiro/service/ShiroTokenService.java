package com.leone.mvc.shiro.service;

import com.leone.mvc.shiro.base.ShiroModuleProperties;
import com.leone.mvc.shiro.base.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *
 * @author leone
 **/
@Service
public class ShiroTokenService {

    private final Logger logger = LoggerFactory.getLogger(ShiroTokenService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ShiroModuleProperties shiroModuleProperties;

    /**
     * 登录
     *
     * @param userId
     * @return
     */
    public String login(String userId) {
        return updateToken(userId, null);
    }

    /**
     * 登录
     *
     * @param userId
     * @param role
     * @return
     */
    public String login(String userId, String role) {
        return updateToken(userId, role);
    }

    /**
     * @param userId
     * @param role
     * @return
     */
    private String updateToken(String userId, String role) {
        String salt = (int) (Math.random() * 100000) + "";

        String tokenPrefix = this.shiroModuleProperties.getTokenPrefix();
        String tokenName = this.shiroModuleProperties.getTokenName();
        int cacheDays = this.shiroModuleProperties.getCacheDays();
        if (null == userId || "".equals(userId)) {
            return null;
        }
        String token;
        if (null != role && !"".equals(role)) {
            token = TokenUtil.encode(userId + "." + role + "." + salt);
        } else {
            token = TokenUtil.encode(userId + "." + "-1" + "." + salt);
        }
        logger.info("setToken userId:{}=token:{}", userId, token);
        this.stringRedisTemplate.opsForValue().set(tokenPrefix + tokenName + userId, token, cacheDays, TimeUnit.DAYS);
        String value = this.stringRedisTemplate.opsForValue().get(tokenPrefix + tokenName + userId);
        return token;
    }

    /**
     * 登出
     *
     * @param userId
     */
    public void logout(String userId) {
        if (!shiroModuleProperties.isMultiLogin()) {
            String tokenPrefix = shiroModuleProperties.getTokenPrefix();
            String tokenName = shiroModuleProperties.getTokenName();
            String token = stringRedisTemplate.opsForValue().get(tokenPrefix + tokenName + userId);
            stringRedisTemplate.delete(tokenPrefix + tokenName + userId);
        }
    }

    /**
     * 更新用户角色
     *
     * @param userId
     * @param role
     */
    public void updateRole(String userId, String role) {
        String tokenPrefix = shiroModuleProperties.getTokenPrefix();
        String tokenName = shiroModuleProperties.getTokenName();
        Integer cacheDays = shiroModuleProperties.getCacheDays();
        if (!StringUtils.isEmpty(role)) {
            stringRedisTemplate.delete(tokenPrefix + tokenName + userId);
            String token = TokenUtil.encode(userId + "." + role);
            stringRedisTemplate.opsForValue().set(tokenPrefix + tokenName + userId, token, cacheDays, TimeUnit.DAYS);
        }
    }

}

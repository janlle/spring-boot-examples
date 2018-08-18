package com.andy.mvc.shiro.service;

import com.andy.mvc.shiro.base.ShiroModuleProperties;
import com.andy.mvc.shiro.base.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class ShiroTokenService {

    private final Logger logger = LoggerFactory.getLogger(ShiroTokenService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ShiroModuleProperties shiroModuleProperties;

    public String login(String userId) {
        return updateToken(userId, null);
    }

    public String login(String userId, String role) {
        return updateToken(userId, role);
    }

    private String updateToken(String userId, String role) {
//        String prefix = shiroModuleProperties.getPrefix();
//        Integer cacheDays = shiroModuleProperties.getCacheDays();
//        stringRedisTemplate.opsForValue().set(prefix + shiroModuleProperties.getToken_to_user_id() + token, userId, cacheDays, TimeUnit.DAYS);
//        if (!shiroModuleProperties.isMultiLogin()) {
//            stringRedisTemplate.opsForValue().set(prefix + shiroModuleProperties.getUser_id_to_token() + userId, token, cacheDays, TimeUnit.DAYS);
//        }
//        if (!StringUtils.isEmpty(role)) {
//            stringRedisTemplate.opsForValue().set(prefix + shiroModuleProperties.getUser_id_to_role() + userId, role, cacheDays, TimeUnit.DAYS);
//        }

        String salt = (int) (Math.random() * 10000) + "";

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
        System.out.println(value);
        return token;
    }

    public void logout(String userId) {

//        if (!shiroModuleProperties.isMultiLogin()) {
//            String prefix = shiroModuleProperties.getPrefix();
//            String token = stringRedisTemplate.opsForValue().get(prefix + shiroModuleProperties.getUser_id_to_token() + userId);
//            stringRedisTemplate.delete(prefix + shiroModuleProperties.getToken_to_user_id() + token);
//            stringRedisTemplate.delete(prefix + shiroModuleProperties.getUser_id_to_token() + userId);
//            stringRedisTemplate.delete(prefix + shiroModuleProperties.getUser_id_to_role() + userId);
//        }
        if (!shiroModuleProperties.isMultiLogin()) {
            String tokenPrefix = shiroModuleProperties.getTokenPrefix();
            String tokenName = shiroModuleProperties.getTokenName();
            String token = stringRedisTemplate.opsForValue().get(tokenPrefix + tokenName + userId);
            stringRedisTemplate.delete(tokenPrefix + tokenName + userId);
        }

    }

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

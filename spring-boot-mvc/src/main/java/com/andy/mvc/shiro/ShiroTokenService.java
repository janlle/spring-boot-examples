package com.andy.mvc.shiro;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ShiroTokenService {
    @Resource(
        name = "stringRedisTemplate"
    )
    private ValueOperations<String, String> redis;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ShiroProperties shiroProperties;

    public ShiroTokenService() {
    }

    public void afterLogin(Integer userId, String token) {
        this.updateToken(userId, token, (String)null);
    }

    public void afterLogin(Integer userId, String token, String role) {
        this.updateToken(userId, token, role);
    }

    private void updateToken(Integer userId, String token, String role) {
        String prefix = this.shiroProperties.getPrefix();
        int cacheDays = this.shiroProperties.getCacheDays();
        this.redis.set(prefix + "auth.token.id:" + token, "" + userId, (long)cacheDays, TimeUnit.DAYS);
        if (!this.shiroProperties.isMultiLogin()) {
            this.redis.set(prefix + "auth.id.token:" + userId, token, (long)cacheDays, TimeUnit.DAYS);
        }

        if (!StringUtils.isEmpty(role)) {
            this.redis.set(prefix + "auth.id.role:" + userId, role, (long)cacheDays, TimeUnit.DAYS);
        }

    }

    public void afterLogout(Integer userId) {
        if (!this.shiroProperties.isMultiLogin()) {
            String prefix = this.shiroProperties.getPrefix();
            String token = (String)this.redis.get(prefix + "auth.id.token:" + userId);
            this.stringRedisTemplate.delete(prefix + "auth.token.id:" + token);
            this.stringRedisTemplate.delete(prefix + "auth.id.token:" + userId);
            this.stringRedisTemplate.delete(prefix + "auth.id.role:" + userId);
        }
    }
}

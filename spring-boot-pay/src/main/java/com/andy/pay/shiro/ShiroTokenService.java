package com.andy.pay.shiro;

import com.andy.pay.shiro.config.ShiroProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class ShiroTokenService {

//    @Resource(name = "stringRedisTemplate")
//    private ValueOperations<String, String> redis;
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Resource
//    private ShiroProperty shiroProperty;
//
//    public void afterLogin(Integer userId, String token) {
//        this.updateToken(userId, token, null);
//    }
//
//    public void afterLogin(Integer userId, String token, String role) {
//        this.updateToken(userId, token, role);
//    }
//
//    private void updateToken(Integer userId, String token, String role) {
//        String prefix = this.shiroProperty.getPrefix();
//
//        int cacheDays = this.shiroProperty.getCacheDays();
//
//        this.redis.set(prefix + "auth.token.id:" + token, "" + userId, (long) cacheDays, TimeUnit.DAYS);
//        if (!this.shiroProperty.isMultiLogin()) {
//            this.redis.set(prefix + "auth.id.token:" + userId, token, (long) cacheDays, TimeUnit.DAYS);
//        }
//
//        if (!StringUtils.isEmpty(role)) {
//            this.redis.set(prefix + "auth.id.role:" + userId, role, (long) cacheDays, TimeUnit.DAYS);
//        }
//
//    }
//
//    public void afterLogout(Integer userId) {
//
//        if (!this.shiroProperty.isMultiLogin()) {
//            String prefix = this.shiroProperty.getPrefix();
//            String token = this.redis.get(prefix + "auth.id.token:" + userId);
//            this.stringRedisTemplate.delete(prefix + "auth.token.id:" + token);
//            this.stringRedisTemplate.delete(prefix + "auth.id.token:" + userId);
//            this.stringRedisTemplate.delete(prefix + "auth.id.role:" + userId);
//        }
//    }
}

package com.andy.pay.shiro;

import com.andy.pay.shiro.config.ShiroProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Leone
 * @since: 2018-08-05
 **/
@Service
public class TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ShiroProperty shiroProperty;

    public void login(String userId) {
        this.updateToken(userId, null);
    }

    public void login(String userId, String role) {
        this.updateToken(userId, role);
    }

    public void logout(String userId) {
        if (!this.shiroProperty.isMultiLogin()) {
            String prefix = this.shiroProperty.getRedisPrefix();
            this.stringRedisTemplate.delete(prefix + "auth.token:" + userId);
        }
    }

    private void updateToken(String userId, String role) {
        String prefix = this.shiroProperty.getRedisPrefix();
        int cacheDays = this.shiroProperty.getCacheDays();
        if (null == userId || "".equals(userId)) {
            return;
        }
        String token;
        if (null != role || "".equals(role)) {
            token = TokenUtil.encode(userId + "." + role);
        } else {
            token = TokenUtil.encode(userId + "." + "token");
        }
        LOGGER.info("setToken:{}={}", userId, token);
        this.stringRedisTemplate.opsForValue().set(prefix + ".auth.token:" + userId, token, cacheDays, TimeUnit.DAYS);
        String value = this.stringRedisTemplate.opsForValue().get(prefix + ".auth.token:" + userId);
//        LOGGER.info("getToken:{}", value);
    }


}

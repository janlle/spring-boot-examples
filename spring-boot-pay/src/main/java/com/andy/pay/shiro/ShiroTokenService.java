package com.andy.pay.shiro;

import com.andy.pay.shiro.config.ShiroProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

@Service
public class ShiroTokenService {

    private static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ShiroProperty shiroProperty;

    public void afterLogin(String userId) {
        this.updateToken(userId, null);
    }

    public void afterLogin(String userId, String role) {
        this.updateToken(userId, role);
    }

    private void updateToken(String userId, String role) {
        String prefix = this.shiroProperty.getRedisPrefix();
        int cacheDays = this.shiroProperty.getCacheDays();
        if (null == userId || "".equals(userId)) {
            return;
        }
        String token;
        if (null != role && "".equals(role)) {
            token = MD5(userId + "." + role);
        } else {
            token = MD5(userId + "." + "token");
        }
        // key -- value   == userId.role
        this.stringRedisTemplate.opsForValue().set(prefix + "auth.token:" + userId, token, cacheDays, TimeUnit.DAYS);
    }

    public void afterLogout(Integer userId) {
        if (!this.shiroProperty.isMultiLogin()) {
            String prefix = this.shiroProperty.getRedisPrefix();
            String token = this.stringRedisTemplate.opsForValue().get(prefix + "auth.token:" + userId);
            this.stringRedisTemplate.delete(prefix + "auth.token:" + userId);
        }
    }

    public static String MD5(String content) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes("UTF-8"));
            byte[] hashCode = messageDigest.digest();
            return HEX_BINARY_ADAPTER.marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}

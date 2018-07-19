package com.andy.pay.shiro;

import com.andy.pay.shiro.config.ShiroProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class ShiroTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRealm.class);

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
            token = AESEncode(userId, userId + "." + role);
        } else {
            token = AESEncode(userId, userId + "." + "token");
        }
        // key -- value   == userId.role
        LOGGER.info("setToken,Key:{}==Value:{}", userId, token);
        this.stringRedisTemplate.opsForValue().set(prefix + "auth.token:" + userId, token, cacheDays, TimeUnit.DAYS);
        System.out.println("---------");
        String value = this.stringRedisTemplate.opsForValue().get(prefix + "auth.token:" + userId);
        System.out.println(AESDecode(userId, value));
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

    public static String AESEncode(String encodeRules, String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
//            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(content.getBytes());
            return new HexBinaryAdapter().marshal(encodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String AESDecode(String encodeRules, String content) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
//            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            Key key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(DatatypeConverter.parseHexBinary(content));
            return new String(decodeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

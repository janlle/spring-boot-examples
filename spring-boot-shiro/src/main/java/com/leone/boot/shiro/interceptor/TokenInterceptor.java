package com.leone.boot.shiro.interceptor;

import com.leone.boot.shiro.common.anno.AuthToken;
import com.leone.boot.shiro.config.TokenProperties;
import com.leone.boot.shiro.exception.ValidateException;
import com.leone.boot.shiro.util.TokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author leone
 * @since 2020-05-29
 **/
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Resource
    private TokenProperties tokenProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod methodHandle = (HandlerMethod) handler;
        Method method = methodHandle.getMethod();

        if (method.getAnnotation(AuthToken.class) != null || methodHandle.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader(tokenProperties.getHeaderName());
            if (ObjectUtils.isEmpty(token)) {
                throw new ValidateException(40001, "Authentication token is empty.");
            } else {
                String userId = TokenUtil.validateToken(token, tokenProperties.getSecret())[0];
                String redisToken = stringRedisTemplate.opsForValue().get(tokenProperties.getRedisPrefix() + ":" + tokenProperties.getTokenPrefix() + ":" + userId);
                if (token.equals(redisToken)) {
                    log.info("token: {}", token);
                    return true;
                } else {
                    throw new ValidateException(40002, "Authentication failure.");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}

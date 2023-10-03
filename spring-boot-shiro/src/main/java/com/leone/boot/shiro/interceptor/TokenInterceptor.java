package com.leone.boot.shiro.interceptor;

import com.leone.boot.shiro.common.Result;
import com.leone.boot.shiro.common.anno.AuthToken;
import com.leone.boot.shiro.config.TokenProperties;
import com.leone.boot.shiro.exception.ValidateException;
import com.leone.boot.shiro.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            if (StringUtils.isEmpty(token)) {
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

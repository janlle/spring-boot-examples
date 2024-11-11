package com.leone.boot.shiro.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author leone
 **/
public class AppExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(AppExceptionResolver.class);

    private static ModelAndView unAuth = new ModelAndView("403");

    private static ModelAndView error = new ModelAndView("500");

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //如果是shiro无权操作转发到未授权页面
        if (ex instanceof UnauthorizedException) {
            log.error("message:{}", ex.getMessage());
            return unAuth;
        } else {
            log.error("message:{}", ex.getMessage());
            return error;
        }
    }

}

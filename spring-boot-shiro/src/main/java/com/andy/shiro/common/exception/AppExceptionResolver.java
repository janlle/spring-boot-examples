package com.andy.shiro.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Leone
 * @since: 2018-04-21 16:51
 **/
@Slf4j
@Component
public class AppExceptionResolver implements HandlerExceptionResolver {

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

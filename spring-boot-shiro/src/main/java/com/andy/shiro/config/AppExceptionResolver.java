package com.andy.shiro.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-04-21 16:51
 **/
@Component
public class AppExceptionResolver implements HandlerExceptionResolver {

    private static ModelAndView unAuth = new ModelAndView("403");

    private static ModelAndView error = new ModelAndView("500");

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //如果是shiro无权操作转发到未授权页面
        if (ex instanceof UnauthorizedException) {
            return unAuth;
        } else {
            return error;
        }
    }

}

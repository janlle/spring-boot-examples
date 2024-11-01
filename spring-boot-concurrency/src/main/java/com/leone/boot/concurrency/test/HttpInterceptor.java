package com.leone.boot.concurrency.test;

import com.leone.boot.concurrency.threadLocal.RequestHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leone
 * @since 2018-05-06
 **/
@Slf4j
public class HttpInterceptor  {

    // @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandler");

        return true;
    }

    // @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        RequestHolder.remove();
    }

}

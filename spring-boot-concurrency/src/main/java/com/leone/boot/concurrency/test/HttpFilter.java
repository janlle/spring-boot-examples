package com.leone.boot.concurrency.test;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author leone
 * @since 2018-05-06
 **/
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("do filter --- {}", Thread.currentThread().getId());
        //RequestHolder.add(Thread.currentThread().getId());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}

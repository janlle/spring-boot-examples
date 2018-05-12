package com.andy.concurrency;

import com.andy.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-06 14:33
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
        RequestHolder.add(Thread.currentThread().getId());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}

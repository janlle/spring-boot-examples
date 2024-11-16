package com.leone.boot.mvc.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 使用 ServletComponentScan 生效
 * @author leone
 * @since 2018-05-13
 **/
@Slf4j
@WebFilter(filterName = "appFilter", urlPatterns = "/*")
public class AppFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("destroy...");
    }
}

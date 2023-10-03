package com.leone.boot.security.web.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author leone
 * @since 2018-04-13
 **/
@WebFilter
public class AppFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AppFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    public void destroy() {
        log.info("filter destroy");
    }
}

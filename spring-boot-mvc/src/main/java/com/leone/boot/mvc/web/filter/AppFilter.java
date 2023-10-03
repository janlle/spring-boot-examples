package com.leone.boot.mvc.web.filter;

import com.leone.boot.mvc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author leone
 * @since 2018-05-13
 **/
@WebFilter(filterName = "webFilter", urlPatterns = "/**")
public class AppFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AppFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("do filter...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("filter destroy...");
    }
}

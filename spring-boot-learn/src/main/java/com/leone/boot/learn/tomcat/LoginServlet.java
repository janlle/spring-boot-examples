package com.leone.boot.learn.tomcat;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2024-12-23
 **/
public class LoginServlet implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        log.info("login servlet init.");
    }

    @Override
    public ServletConfig getServletConfig() {
        log.info("login servlet getServletConfig.");
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("login servlet service.");
        response.getWriter().write("LoginServlet");
    }

    @Override
    public String getServletInfo() {
        return "login-servlet";
    }

    @Override
    public void destroy() {
        log.info("login servlet destroy.");
    }

}

package com.andy.mvc.shiro.filter;

import com.andy.mvc.shiro.base.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Leone
 * @since 2018-05-17
 **/
public class TokenFilter extends AuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            printUnauthorized("auth.token.empty", WebUtils.toHttp(response));
            return false;
        }
        boolean loginSuccess = login(new Token(token));
        if (!loginSuccess) {
            printUnauthorized("auth.token.wrong", WebUtils.toHttp(response));
        }
        return loginSuccess;
    }

    private String getToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        if (!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith("token")) {
            token = getToken(authorizationHeader);
        }
        return token;
    }

    private void printUnauthorized(String messageCode, HttpServletResponse response) {
        String content = String.format("{\"code\":\"%s\",\"message\":\"%s\"}", messageCode, HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setContentType("application/json;charset=UTF-8");
        response.setContentLength(content.length());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try {
            final PrintWriter writer = response.getWriter();
            writer.write(content);
        } catch (IOException e) {
            logger.error("{}", e.getMessage());
        }
    }

    private boolean login(Token token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            logger.error("{}", e.getMessage());
            return false;
        }
    }

    private String getToken(String authorizationHeader) {
        if (authorizationHeader == null) {
            return null;
        }
        String[] authTokens = authorizationHeader.split(" ");
        if (authTokens.length < 2) {
            return null;
        }
        return authTokens[1];
    }

}

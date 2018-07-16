package com.andy.pay.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andy.pay.shiro.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class TokenFilter extends AuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    public TokenFilter() {
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = this.getToken(request);
        if (StringUtils.isEmpty(token)) {
            this.printUnauthorized("auth.token.empty", WebUtils.toHttp(response));
            return false;
        } else {
            boolean loginSuccess = this.login(new Token(token));
            if (!loginSuccess) {
                this.printUnauthorized("auth.token.wrong", WebUtils.toHttp(response));
            }

            return loginSuccess;
        }
    }

    private String getToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        if (!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith("token")) {
            token = this.getToken(authorizationHeader);
        }

        return token;
    }

    private void printUnauthorized(String messageCode, HttpServletResponse response) {
        String content = String.format("{\"code\":\"%s\",\"msg\":\"%s\"}", messageCode, HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setContentType("application/json;charset=UTF-8");
        response.setContentLength(content.length());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        try {
            PrintWriter writer = response.getWriter();
            writer.write(content);
        } catch (IOException var5) {
            logger.warn("", var5);
        }

    }

    private boolean login(Token token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return true;
        } catch (AuthenticationException var3) {
            return false;
        }
    }

    private String getToken(String authorizationHeader) {
        if (authorizationHeader == null) {
            return null;
        } else {
            String[] authTokens = authorizationHeader.split(" ");
            return authTokens.length < 2 ? null : authTokens[1];
        }
    }
}

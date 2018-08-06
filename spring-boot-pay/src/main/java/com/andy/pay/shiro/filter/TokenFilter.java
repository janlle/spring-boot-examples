package com.andy.pay.shiro.filter;

import com.andy.pay.shiro.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
 * @author: lyon
 * @since: 2018-08-05
 **/
public class TokenFilter extends AuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    public TokenFilter() {
        logger.info("token filter init...");
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("onAccessDenied...");
        String token = this.getToken(request);
        if (StringUtils.isEmpty(token)) {
            this.writeError("auth.token.empty", WebUtils.toHttp(response));
            return false;
        } else {
            boolean loginSuccess = login(new Token(token));
            System.out.println(loginSuccess);
            if (!loginSuccess) {
                this.writeError("auth.token.wrong", WebUtils.toHttp(response));
            }
            return loginSuccess;
        }
    }

//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        return true;
//    }


//    @Override
//    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        return super.onPreHandle(request, response, mappedValue);
//    }


//    @Override
//    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
//        super.postHandle(request, response);
//    }

    private String getToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith("token")) {
            String[] authTokens = authorizationHeader.split(" ");
            return authTokens.length < 2 ? null : authTokens[1];
        }
        return null;
    }


    private void writeError(String message, HttpServletResponse response) {
        String content = String.format("{\"code\":\"%s\",\"message\":\"%s\"}", HttpStatus.UNAUTHORIZED, message);
        response.setContentType("application/json;charset=UTF-8");
        response.setContentLength(content.length());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try (PrintWriter writer = response.getWriter()) {
            writer.write(content);
        } catch (IOException e) {
            logger.warn("输出异常信息失败!", e);
        }
    }
    private boolean login(Token token) {
        try {
            SecurityUtils.getSubject().login(token);
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }
    }

}

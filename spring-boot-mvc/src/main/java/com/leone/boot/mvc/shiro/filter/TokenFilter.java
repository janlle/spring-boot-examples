package com.leone.boot.mvc.shiro.filter;

import com.leone.boot.mvc.shiro.Token;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author leone
 **/
// public class TokenFilter extends AuthenticationFilter {
//
//     private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
//
//     @Override
//     protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//         String token = getToken(request);
//         if (StringUtils.isEmpty(token)) {
//             print("auth token empty", WebUtils.toHttp(response));
//             return false;
//         }
//         boolean loginSuccess = login(new Token(token));
//         if (!loginSuccess) {
//             print("auth token wrong", WebUtils.toHttp(response));
//         }
//         return loginSuccess;
//     }
//
//     /**
//      * 从request获取token信息
//      *
//      * @param request
//      * @return
//      */
//     private String getToken(ServletRequest request) {
//         HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
//         String authorizationHeader = httpServletRequest.getHeader("Authorization");
//         return ObjectUtils.isEmpty(authorizationHeader) ? null : authorizationHeader;
//     }
//
//     /**
//      * 响应异常信息
//      *
//      * @param message
//      * @param response
//      */
//     private void print(String message, HttpServletResponse response) {
//         String content = String.format("{\"code\":\"%s\",\"message\":\"%s\"}", HttpStatus.UNAUTHORIZED.value(), message);
//         response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//         response.setContentLength(content.length());
//         response.setStatus(HttpStatus.UNAUTHORIZED.value());
//         try (PrintWriter writer = response.getWriter()) {
//             writer.write(content);
//         } catch (IOException e) {
//             logger.error("{}", e.getMessage());
//         }
//     }
//
//     /**
//      * @param token
//      * @return
//      */
//     private boolean login(Token token) {
//         try {
//             Subject subject = SecurityUtils.getSubject();
//             subject.login(token);
//             return true;
//         } catch (AuthenticationException e) {
//             logger.error("{}", e.getMessage());
//             return false;
//         }
//     }
//
// }

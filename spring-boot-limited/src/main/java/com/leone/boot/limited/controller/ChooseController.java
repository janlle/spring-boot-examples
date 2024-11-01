package com.leone.boot.limited.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-19
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class ChooseController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/choose")
    public Map<String, Object> choose(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new LinkedHashMap<>();

        Map<String, Object> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        result.put("headers", headerMap);

        HttpSession session = request.getSession();
        Map<String, Object> sessionMap = new HashMap<>();
        Enumeration<String> attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            String value = request.getHeader(name);
            sessionMap.put(name, value);
        }
        result.put("sessions", sessionMap);


        Map<String, Object> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        result.put("cookies", cookieMap);

        log.info("port: {} address: {}", port, request.getRemoteAddr());
        result.put("AuthType", request.getAuthType());
        result.put("ContextPath", request.getContextPath());
        result.put("Method", request.getMethod());
        result.put("PathInfo", request.getPathInfo());
        result.put("RequestedSessionId", request.getRequestedSessionId());
        result.put("RemoteUser", request.getRemoteUser());
        result.put("RemoteAddr", request.getRemoteAddr());
        result.put("ServerName", request.getServerName());
        result.put("LocalPort", request.getLocalPort());
        result.put("RemotePort", request.getRemotePort());
        result.put("ServerPort", request.getServerPort());
        result.put("Scheme", request.getScheme());
        result.put("LocalAddr", request.getLocalAddr());
        result.put("Protocol", request.getProtocol());
        result.put("RequestURI", request.getRequestURI());
        result.put("RequestURL", request.getRequestURL());
        return result;
    }

}

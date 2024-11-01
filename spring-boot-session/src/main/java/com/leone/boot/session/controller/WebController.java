package com.leone.boot.session.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.servlet.http.HttpServletRequest;;
// import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 * @since 2018-06-08
 **/
@RestController
@RequestMapping("/api/session")
public class WebController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/get")
    public Object get(HttpServletRequest request, @RequestHeader Map<String, Object> headers) {
        System.out.println(port + " get");
        Map<String, Object> map = new HashMap<>();
        map.put("serverPort", port);
        Object userName = request.getSession().getAttribute("user");
        map.put("user", userName);
        map.put("sessionId", request.getSession().getId());
        map.put("creationTime", request.getSession().getCreationTime());
        map.put("lastAccessedTim", request.getSession().getLastAccessedTime());
        map.put("maxInactiveInterval", request.getSession().getMaxInactiveInterval());
        return map;
    }

    @GetMapping("/set")
    public Object set(HttpServletRequest request) {
        System.out.println(port + " set");
        HttpSession session = request.getSession();
        session.setAttribute("user", "james");
        return session.getAttribute("user");
    }


}
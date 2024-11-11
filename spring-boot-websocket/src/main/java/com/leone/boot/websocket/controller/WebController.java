package com.leone.boot.websocket.controller;

import com.leone.boot.starter.custom.config.CustomService;
import com.leone.boot.websocket.config.Constant;
import com.leone.boot.websocket.config.WsMessage;
import com.leone.boot.websocket.config.WsResponse;
import com.leone.boot.websocket.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author leone
 * @since 2018-07-06
 **/
@RestController
// 局部跨域
@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Resource
    private WebSocketService webSocketService;

    @Resource
    private CustomService customService;

    @RequestMapping("/say")
    public String say() {
        return customService.say();
    }

    @RequestMapping("/name")
    public String getName(HttpServletRequest request, @RequestParam(required = false) String name) {
        log.info("name: {}", name);
        return "origin-name: " + name;
    }

    // @MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @MessageMapping(Constant.FORE_TO_SERVER_PATH)
    @SendTo(Constant.PRODUCER_PATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public WsResponse say(WsMessage message) {
        // 此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        List<String> users = Collections.singletonList("d892bf12bf7d11e793b69c5c8e6f60fb");
        webSocketService.send2Users(users, new WsResponse("admin hello"));
        return new WsResponse("Welcome, " + message.getName() + "!");
    }


}

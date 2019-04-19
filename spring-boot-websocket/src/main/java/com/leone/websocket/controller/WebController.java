package com.leone.websocket.controller;

import com.leone.websocket.config.Constant;
import com.leone.websocket.config.WsMessage;
import com.leone.websocket.config.WsResponse;
import com.leone.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * @author Leone
 * @since 2018-07-06
 **/
@Slf4j
@RestController
@RequestMapping("/api")
// 局部跨域
@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
public class WebController {

    @Resource
    private WebSocketService webSocketService;

    @RequestMapping("/hello")
    public String socket(HttpServletRequest request, HttpServletResponse response) {
        return "success";
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

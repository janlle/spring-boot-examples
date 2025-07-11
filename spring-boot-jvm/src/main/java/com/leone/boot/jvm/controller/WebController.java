package com.leone.boot.jvm.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.leone.boot.jvm.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leone
 * @since 2018-07-14
 **/

@RestController
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private DataService dataService;

    @GetMapping("/newObject")
    public String newObject(@RequestParam Integer count) throws Exception {
        dataService.newObjects(count);
        return "count:" + count;
    }

    @GetMapping("/stopNewObject")
    public String stopNewObject() {
        dataService.stopNewObject();
        return "Stop new object success.";
    }

    @GetMapping("/newThread")
    public String newThread(@RequestParam Integer count) throws Exception {
        dataService.newThread(count);
        log.info("New thread.");
        return "count:" + count;
    }

    @GetMapping("/stopNewThread")
    public String stopNewThread() {
        dataService.stopNewThread();
        return "Stop new thread success.";
    }

    @GetMapping("/test")
    public String test() {
        return "success.";
    }

    // 测试登录，浏览器访问： http://localhost:8080/login?username=zhang&password=123456
    @RequestMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            // 第1步，先登录上
            StpUtil.login(10001);
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return tokenInfo.toString();
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}

package com.andy.mail.controller;


import com.andy.mail.common.BaseResult;
import com.andy.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: lyon
 * @since: 2018-05-09
 **/
@Slf4j
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/")
    public String home() {
        return "success";
    }

    @ResponseBody
    @GetMapping("/send")
    public BaseResult<Object> login(String to, HttpServletRequest request) {
        boolean flag = false;
        try {
            flag = mailService.sendFromThymeleaf(to, "激活邮件");
            log.info("发送邮件{}", flag ? "成功":"失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResult.success(flag);
    }

    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String,Object> map){
        map.put("hello", "thymeleaf渲染");
        return "/htmlMail";
    }

    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("hello", "freemarker渲染");
        map.put("code", "admin");
        return "ftlMail";
    }

    @RequestMapping("/getCode")
    public String get(String code, HttpServletResponse response, HttpServletRequest request){

        return "SUCCESS";
    }


}

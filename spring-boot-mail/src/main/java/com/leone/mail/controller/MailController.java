package com.leone.mail.controller;


import com.leone.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leone
 * @since 2018-05-09
 **/
@Slf4j
@Controller
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    private static Map<String, Object> content = new HashMap<>();

    static {
        content.put("title", "标题");
        content.put("content", "http://www.baidu.com");
        content.put("to", "james@gmail.com");
    }

    @ResponseBody
    @GetMapping("/send/ftl")
    public String sendFtlMain(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = mailService.sendFreemarkerMail(to, subject, content);
        return String.valueOf(flag);
    }

    @ResponseBody
    @GetMapping("/send/html")
    public String sendHtmlMain(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = mailService.sendThymeleafMail(to, subject, content);
        return String.valueOf(flag);
    }

    @ResponseBody
    @GetMapping("/send/simple")
    public String sendSimpleMain(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = mailService.sendSimpleMail(to, subject, content);
        return String.valueOf(flag);
    }

    @ResponseBody
    @GetMapping("/send/htm")
    public String sendAttachmentsMail(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = mailService.sendAttachmentsMail(to, subject, content);
        return String.valueOf(flag);
    }


    @RequestMapping("/html")
    public String helloHtml(Map<String, Object> map) {
        map.putAll(content);
        return "/htmlMail";
    }

    @RequestMapping("/ftl")
    public String helloFtl(Map<String, Object> map) {
        map.putAll(content);
        return "ftlMail";
    }

}

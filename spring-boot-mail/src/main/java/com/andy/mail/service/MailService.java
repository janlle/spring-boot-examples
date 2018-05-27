package com.andy.mail.service;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-09
 **/
@Slf4j
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;


    private static final String THYMELEAF_TEMPLATE_PATH = "htmlMail";

    private static final String FREEMARKER_TEMPLATE_PATH = "templates/freemarker/ftlMail.ftl";


    public void sendFromFreemarker(String to, String subject) throws Exception {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(InternetAddress.parse(to));
            helper.setSubject("【" + subject + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");

            Map<String, Object> model = new HashMap<>();
            model.put("params", "hello");
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(FREEMARKER_TEMPLATE_PATH);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(text, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean sendFromThymeleaf(String to, String subject) throws Exception {
        String token = storage(to);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("to", to);
            IContext context = new Context(Locale.CHINESE, map);
            String process = templateEngine.process(THYMELEAF_TEMPLATE_PATH, context);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
            log.info("发送邮件,[邮件发送成功]->{}!", to);
        } catch (Exception e) {
            log.error("发送邮件,[邮件发送失败]->{}!", to);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String storage(String to) {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

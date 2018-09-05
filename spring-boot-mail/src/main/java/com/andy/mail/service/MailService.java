package com.andy.mail.service;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
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
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * @author Leone
 * @since: 2018-05-09
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
//        String token = storage(to);
        String token = "xxx";
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



    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            javaMailSender.send(message);
            log.info("简单邮件已经发送。");
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常！", e);
        }
    }

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
        }
    }


    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            //helper.addAttachment("test"+fileName, file);

            javaMailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常！", e);
        }
    }


    /**
     * 发送正文中有静态资源（图片）的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            javaMailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }

}

package com.leone.boot.mvc.mail;


import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author leone
 * @since 2018-05-09
 **/
@Slf4j
@Controller
@RequestMapping("/mail")
public class MailController {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    // @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private static final String THYMELEAF_TEMPLATE = "/htmlMail";

    private static final String FREEMARKER_TEMPLATE = "ftlMail.ftl";

    private static final String filePath = "hello.zip";


    private static final Map<String, Object> content = new HashMap<>();

    static {
        content.put("title", "标题");
        content.put("content", "http://www.baidu.com");
        content.put("to", "james@gmail.com");
    }

    @ResponseBody
    @GetMapping("/send/ftl")
    public String sendFtlMain(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = sendFreemarkerMail(to, subject, content);
        return String.valueOf(flag);
    }

    @ResponseBody
    @GetMapping("/send/html")
    public String sendHtmlMain(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = sendThymeleafMail(to, subject, content);
        return String.valueOf(flag);
    }

    @ResponseBody
    @GetMapping("/send/simple")
    public String sendSimpleMain(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = sendSimpleMail(to, subject, content);
        return String.valueOf(flag);
    }

    @ResponseBody
    @GetMapping("/send/htm")
    public String sendAttachmentsMail(String to, String subject, String content, HttpServletRequest request) {
        boolean flag = sendAttachmentsMail(to, subject, content);
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

    /**
     * 发送freemarker模板mail
     *
     * @param to
     * @param subject
     */
    public boolean sendFreemarkerMail(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(InternetAddress.parse(to));
            helper.setSubject("[" + subject + " " + LocalDate.now() + "]");
            Map<String, Object> model = new HashMap<>();
            model.put("subject", subject);
            model.put("content", content);
            model.put("to", to);
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(FREEMARKER_TEMPLATE);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(text, true);
            javaMailSender.send(mimeMessage);
            log.info("send mail success!");
            return true;
        } catch (Exception e) {
            log.error("send mail filed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 发送thymeleaf邮件
     *
     * @param to
     * @param subject
     */
    public boolean sendThymeleafMail(String to, String subject, String content) {
        //        String token = storage(to);
        String token = "xxx";
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, CHARSET.displayName());
            Map<String, Object> map = new HashMap<>();
            map.put(content, token);
            map.put("content", content);
            map.put("subject", subject);
            map.put("to", to);
            IContext context = new Context(Locale.CHINESE, map);
            String process = templateEngine.process(THYMELEAF_TEMPLATE, context);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("[" + subject + " " + LocalDate.now() + "]");
            messageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
            log.info("send mail success!");
            return true;
        } catch (Exception e) {
            log.error("send mail filed:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 发送文本邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public boolean sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            javaMailSender.send(message);
            log.info("send mail success!");
            return true;
        } catch (Exception e) {
            log.error("send mail filed:{}", e);
            return false;
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public boolean sendAttachmentsMail(String to, String subject, String content) {
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
            log.info("send mail success!");
            return true;
        } catch (MessagingException e) {
            log.error("send mail filed:{}", e);
            return false;
        }
    }


    /**
     * 发送正文中有静态资源（图片）的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
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
            log.info("send mail success!");
            return true;
        } catch (MessagingException e) {
            log.error("send mail filed:{}", e);
            return false;
        }
    }

}

//package com.andy.pay.web.controller;
//
//import com.andy.pay.common.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @author: Mr.ruoLin
// * @since: 2018-05-10
// **/
//@Api(value = "发送消息所有api", description = "消息模块操作接口,注意传入参数与类型!")
//@Slf4j
//@Controller
//@RequestMapping("/message")
//public class MessageController {
//
//    @Autowired
//    private MessageService messageService;
//
//    private String subject = "";
//
//    private String from;
//
//
//    /**
//     * 发送邮件接口
//     * @param to
//     * @return
//     */
//    @ApiOperation(value = "发送邮件接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiImplicitParam(name = "mail",value ="用户邮箱",required = true, dataType = "String")
//    @ResponseBody
//    @GetMapping("/sendMail")
//    public Result<String> sendMail(@RequestParam(value = "to", required = true) String to) {
//        if (messageService.sendMail(from, to, subject)) {
//            return Result.success("发送邮件成功！");
//        }
//        return Result.error("发送邮件失败！");
//    }
//
//
//    @GetMapping("/test")
//    public String test(String to, ModelMap model) {
//        model.put("to", to);
//        model.put("code", "128323");
//        return "htmlMail";
//    }
//
//
//    @ApiOperation(value = "验证邮件token接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "mail",value ="用户邮箱",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "token",value ="激活邮件中的token",required = true, dataType = "String")
//    })
//    @ResponseBody
//    @GetMapping("/activateMail")
//    public Result<String> activateMail(@RequestParam(value = "token", defaultValue = "n", required = false) String token,
//                                           @RequestParam(value = "mail", defaultValue = "e", required = false) String mail) {
//        if (messageService.activateMail(token, mail)) {
//            return Result.success("用户激活成功！");
//        }
//        return Result.error("用户激活失败！");
//    }
//
//    @ApiOperation(value = "验证短息验证码接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone",value ="用户手机号码",required = true, dataType = "String"),
//            @ApiImplicitParam(name = "code",value ="六位验证码",required = true, dataType = "String")
//    })
//    @ResponseBody
//    @GetMapping("/activateSms")
//    public Result<String> activateSms(@RequestParam("phone") String phone, @RequestParam("code") String code) {
//        if (phone == null || phone.length() != 11) {
//            return Result.error("手机号码格式不正确！");
//        }
//        try {
//            messageService.activateSms(phone, code);
//        } catch (Exception e) {
//            log.error("验证失败！{}", e.getMessage());
//            return Result.error(e.getMessage());
//        }
//        return Result.success("用户验证成功！");
//    }
//
//
//    @ResponseBody
//    @ApiOperation(value = "用户获取验证码接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiImplicitParam(name = "phone",value ="用户手机号码",required = true, dataType = "String")
//    @GetMapping("/sendSmsCode")
//    public Result<String> smsCode(@RequestParam("phone") String phone) {
//        if (phone == null || phone.length() != 11) {
//            return Result.error("手机号码格式不正确！");
//        }
//        try {
//            messageService.sendSms(phone);
//        } catch (Exception e) {
//            log.error("验证码发送失败！手机号码为：{}", phone);
//            return Result.error("验证码发送失败，请稍后重试！");
//        }
//        return Result.success("验证码已发送!");
//    }
//
//}

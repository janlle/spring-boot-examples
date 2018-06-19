package com.andy.pay.web.controller;

import com.andy.pay.common.utils.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-17 19:59
 **/
@Slf4j
@Controller
@Api(tags ="web")
@RequestMapping(value = "/")
public class WebController {

    @ApiOperation(value = "生成二维码", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/code")
    public void code(@RequestParam(required = false, defaultValue = "http://www.baidu.com") String content, HttpServletRequest request, HttpServletResponse response) {
        QRCodeUtil.createQRCode(content, response);
    }

}

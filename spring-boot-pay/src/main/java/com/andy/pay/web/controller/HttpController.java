package com.andy.pay.web.controller;

import com.andy.pay.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leone
 * @since 2018-06-17 13:23
 **/
@Slf4j
@Api(tags = "Http测试控制器")
@RestController
@RequestMapping(value = "/api/http")
public class HttpController {

    @ApiOperation(value = "get请求")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get(HttpServletRequest request, HttpServletResponse response, String return_msg, String return_code) {
        log.info("get请求");
        return Result.build(20000, return_msg, null);
    }


    @ApiOperation(value = "post请求")
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Object post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream in = request.getInputStream();
        Document document = reader.read(in);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        in.close();
        log.info("post请求");
        return map;
    }

    @ApiOperation(value = "post请求")
    @RequestMapping(value = "/post1", method = RequestMethod.POST)
    public Object pust1(HttpServletRequest request, HttpServletResponse response) {
        log.info("put请求");
        return Result.build(20000, "SUCCESS", null);
    }

    @ApiOperation(value = "post请求")
    @RequestMapping(value = "/post2", method = RequestMethod.POST)
    public Object post2(HttpServletRequest request, HttpServletResponse response, String return_msg, String return_code) {
        log.info("put请求");
        return Result.build(20000, "SUCCESS", null);
    }

    @ApiOperation(value = "put请求")
    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public Object put(HttpServletRequest request, HttpServletResponse response) {
        log.info("put请求");
        return Result.build(20000, "SUCCESS", null);
    }


    @ApiOperation(value = "delete请求")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        log.info("delete请求");
        return "success";
    }


    @ApiOperation(value = "head请求")
    @RequestMapping(value = "/head", method = RequestMethod.HEAD)
    public String head(HttpServletRequest request, HttpServletResponse response) {
        log.info("head请求");
        return "success";
    }

    @ApiOperation(value = "options请求")
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public String options(HttpServletRequest request, HttpServletResponse response) {
        log.info("options请求");
        return "success";
    }

    @ApiOperation(value = "trace请求")
    @RequestMapping(value = "/trace", method = RequestMethod.TRACE)
    public String trace(HttpServletRequest request, HttpServletResponse response) {
        log.info("trace请求");
        return "success";
    }

    @ApiOperation(value = "patch请求")
    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public String patch(HttpServletRequest request, HttpServletResponse response) {
        log.info("patch请求");
        return "success";
    }


}

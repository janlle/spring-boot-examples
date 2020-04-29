package com.leone.boot.layui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * @author leone
 * @since 2020-04-29
 **/
@Controller
public class WebController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}

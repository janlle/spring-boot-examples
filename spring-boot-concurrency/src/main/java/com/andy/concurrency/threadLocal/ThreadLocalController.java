package com.andy.concurrency.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Leone
 * @since 2018-05-06
 **/
@Slf4j
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @ResponseBody
    @RequestMapping("/test")
    public Long test(){
        log.info("threadLocal test method...");
        return RequestHolder.getId();
    }

}

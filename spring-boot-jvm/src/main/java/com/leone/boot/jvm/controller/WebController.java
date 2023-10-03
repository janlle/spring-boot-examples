package com.leone.boot.jvm.controller;

import com.leone.boot.jvm.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leone
 * @since 2018-07-14
 **/

@Slf4j
@RestController
public class WebController {

    @Autowired
    private DataService dataService;

    @GetMapping("/newObject")
    public String newObject(@RequestParam Integer count) throws Exception {
        dataService.newObjects(count);
        return "count:" + count;
    }

    @GetMapping("/stopNewObject")
    public String stopNewObject() {
        dataService.stopNewObject();
        return "Stop new object success.";
    }

    @GetMapping("/newThread")
    public String newThread(@RequestParam Integer count) throws Exception {
        dataService.newThread(count);
        log.info("New thread.");
        return "count:" + count;
    }

    @GetMapping("/stopNewThread")
    public String stopNewThread() {
        dataService.stopNewThread();
        return "Stop new thread success.";
    }

    @GetMapping("/test")
    public String test() {
        return "success.";
    }

}

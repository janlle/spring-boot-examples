package com.andy.jvm.controller;

import com.andy.jvm.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Leone
 * @since: 2018-07-14 14:11
 **/

@Slf4j
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/bytes")
    public String add(@RequestParam Integer count) throws Exception {
        dataService.addObjects(count);
        return "count:" + count;
    }

    @GetMapping("/stop")
    public String stop() {
        dataService.stop();
        return "stop";
    }


}

package com.leone.boot.log.app;

import com.leone.boot.log.app.entity.AppLogEntity;
import com.leone.boot.log.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-29
 **/
@RestController
@RequestMapping("/api/log")
public class AppLogController {

    @Autowired
    private KafkaSender kafkaSender;

    @PostMapping("/app")
    public String accept(@RequestBody AppLogEntity logEntity) {
        //kafkaSender.send("app-log", logEntity);
        System.out.println(logEntity);
        return "success";
    }

}

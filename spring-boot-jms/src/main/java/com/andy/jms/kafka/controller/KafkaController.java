package com.andy.jms.kafka.controller;

import com.andy.jms.kafka.service.KafkaSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "kafka test api")
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    @ApiOperation("发送消息的接口")
    @GetMapping("/kafka/{topic}")
    public String send(@PathVariable("topic") String topic, @RequestParam String message) {
        try {
            kafkaSender.send(topic, message);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

}
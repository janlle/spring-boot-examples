package com.andy.jms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api("kafka测试接口")
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation("发送消息的接口")
    @GetMapping("/send/kafka/{msg}")
    public String send(@PathVariable("msg")String message) {
        try {
            log.info("kafka的消息={}", message);
            kafkaTemplate.send("test", "key", message);
            log.info("发送kafka成功.");
            return "发送kafka成功";
        } catch (Exception e) {
            log.error("发送kafka失败", e);
            return "发送kafka失败";
        }
    }

}
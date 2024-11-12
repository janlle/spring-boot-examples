package com.leone.boot.mq.controller;

import com.leone.boot.mq.kafka.KafkaSender;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@RestController
@RequestMapping("/api/kafka")
public class RocketController {

    @Autowired
    private RocketMQTemplate rocketmqTemplate;

    @GetMapping("/test")
    public String test() {
        Message<String> msg = MessageBuilder.withPayload("Hello,RocketMQ").build();
        rocketmqTemplate.send("top", msg);
        return "success";
    }

}
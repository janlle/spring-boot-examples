package com.leone.boot.mq.controller;

import com.leone.boot.mq.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SendResult test() {
        Message<String> msg = MessageBuilder.withPayload("Hello,RocketMQ").build();
        SendResult sendResult = rocketmqTemplate.send(topic, msg);
    }

}
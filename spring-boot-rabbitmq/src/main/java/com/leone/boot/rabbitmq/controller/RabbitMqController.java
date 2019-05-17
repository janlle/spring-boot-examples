package com.leone.boot.rabbitmq.controller;

import com.leone.boot.rabbitmq.config.RabbitMQConstant;
import com.leone.boot.rabbitmq.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author leone
 * @since 2018-05-15
 **/
@Slf4j
@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMqController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send/{exchange}")
    public String send(@PathVariable("exchange") String exchange, @RequestParam String message) {
        switch (exchange) {
            case "topic":
                messageSender.sendTopic(RabbitMQConstant.TOPIC_EXCHANGE, RabbitMQConstant.KEY_A, message);
                break;
            case "fanout":
                messageSender.sendFanout(RabbitMQConstant.FANOUT_EXCHANGE, message);
                break;
            case "direct":
                messageSender.sendDirect(RabbitMQConstant.DIRECT_EXCHANGE, message);
                break;
            case "headers":
                messageSender.sendHeaders(RabbitMQConstant.HEADERS_EXCHANGE, message);
                break;
            default:
                messageSender.sendQueue(RabbitMQConstant.QUEUE_A, message);
                break;
        }
        return "send to " + exchange + " success! message: " + message;
    }

}
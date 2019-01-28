package com.andy.rabbitmq.controller;

import com.andy.rabbitmq.config.RabbitMQConstant;
import com.andy.rabbitmq.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leone
 * @since 2018-05-15
 **/
@Slf4j
@RestController
public class RabbitMqController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send/{target}")
    public String send(@PathVariable("target") String target, @RequestParam String message) {
        switch (target) {
            case "topic":
                messageSender.sendTopic(RabbitMQConstant.TOPIC_EXCHANGE, RabbitMQConstant.KEY_A, message);
                break;
            case "fanout":
                messageSender.sendFanout(RabbitMQConstant.FANOUT_EXCHANGE, "fanout类型交换机发送的内容！");
                break;
            case "direct":
                messageSender.sendDirect(RabbitMQConstant.DIRECT_EXCHANGE, "direct类型交换机发送的内容！");
                break;
            case "headers":
                messageSender.sendHeaders(RabbitMQConstant.HEADERS_EXCHANGE, "header类型交换机发送的内容！");
                break;
            default:
                messageSender.sendQueue(RabbitMQConstant.QUEUE_A, message);
                break;
        }
        return "send " + target + " success!";
    }

}
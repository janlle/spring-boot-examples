package com.andy.jms.rabbitmq.controller;

import com.andy.jms.entity.User;
import com.andy.jms.rabbitmq.config.RabbitMQConstant;
import com.andy.jms.rabbitmq.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send/{msg}")
    public String send(@PathVariable("msg") String msg) {
        if (msg.equals("user")) {
            messageSender.queueSend(RabbitMQConstant.QUEUE_A, new User(1L, "james", "admin", new Date(), 10000 + 0.1, new Date(), false));
        } else if (msg.equals("topic")) {
            messageSender.sendTopic(RabbitMQConstant.TOPIC_EXCHANGE, RabbitMQConstant.KEY_A, "topic类型交换机发送的内容！");
        } else if (msg.equals("fanout")) {
            messageSender.sendFanout(RabbitMQConstant.FANOUT_EXCHANGE, "fanout类型交换机发送的内容！");
        } else if (msg.equals("direct")) {
            messageSender.sendTopic(RabbitMQConstant.TOPIC_EXCHANGE, RabbitMQConstant.KEY_A, "direct类型交换机发送的内容！");
        } else if (msg.equals("headers")) {
            messageSender.sendHeaders(RabbitMQConstant.HEADERS_EXCHANGE, "header类型交换机发送的内容！");
        } else {
            messageSender.queueSend(RabbitMQConstant.QUEUE_A, msg);
        }
        return "发送消息成功！";
    }

}
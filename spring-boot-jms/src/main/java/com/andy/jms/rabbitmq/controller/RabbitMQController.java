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

/**
 * @author: Leone
 * @since: 2018-05-15 21:44
 **/
@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send/{target}")
    public String send(@PathVariable(value = "target") String target) {

        String message = "hello james how are you!";

        User user = new User(1L, "james", "admin", new Date(), 10000 + 0.1, new Date(), false);

        if (target.equals("topic")) {
            messageSender.sendTopic(RabbitMQConstant.TOPIC_EXCHANGE, RabbitMQConstant.KEY_A, message);
        } else if (target.equals("fanout")) {
            messageSender.sendFanout(RabbitMQConstant.FANOUT_EXCHANGE, "fanout类型交换机发送的内容！");
        } else if (target.equals("direct")) {
            messageSender.sendDirect(RabbitMQConstant.DIRECT_EXCHANGE, "direct类型交换机发送的内容！");
        } else if (target.equals("headers")) {
            messageSender.sendHeaders(RabbitMQConstant.HEADERS_EXCHANGE, "header类型交换机发送的内容！");
        } else {
            messageSender.sendQueue(RabbitMQConstant.QUEUE_A, message);
        }

        return "发送" + target + "消息SUCCESS!";
    }

}
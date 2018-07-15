package com.andy.jms.rabbitmq.sender;

import com.andy.jms.rabbitmq.config.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate template;

    //-------------------------普通队列模式-------------------------------
    public void queueSend(String queue, Object msg) {
        log.info("send message发送到queue-a队列的消息是:{}", msg);
        template.convertAndSend(queue, msg);
    }

    //-------------------------direct类型的交换机()-------------------------------
    //-------------------------topic类型的交换机()-------------------------------
    public void sendTopic(String exchange, String key, Object msg) {
        template.convertAndSend(exchange, RabbitMQConstant.KEY_A, msg + "a");
        template.convertAndSend(exchange, RabbitMQConstant.KEY_B, msg + "b");
        log.info("send topic message发送到{}交换机,绑定规则为{}的消息是:{}", exchange, RabbitMQConstant.KEY_A, msg);
        log.info("send topic message发送到{}交换机,绑定规则为{}的消息是:{}", exchange, RabbitMQConstant.KEY_B, msg);
    }

    //------------------------- fanout类型的交换机(广播模式)-------------------------------
    public void sendFanout(String exchange, Object msg) {
        template.convertAndSend(exchange, "", msg);
        log.info("send fanout message发送到{}交换机,发送的消息是:{}", exchange, msg);
    }

    //-------------------------headers类型的交换机()-------------------------------
    public void sendHeaders(String exchange, String msg) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("headera", "valuea");
        properties.setHeader("headerb", "valueb");

        Message object = new Message(msg.getBytes(), properties);
        template.convertAndSend(exchange, "", object);
        log.info("send fanout message发送到{}交换机,发送的消息是:{}", exchange, msg);
    }

}
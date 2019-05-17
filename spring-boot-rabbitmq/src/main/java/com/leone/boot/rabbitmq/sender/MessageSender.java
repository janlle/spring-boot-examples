package com.leone.boot.rabbitmq.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author leone
 * @since 2018-06-05
 **/
@Slf4j
@Component
public class MessageSender {

    @Resource
    private AmqpTemplate template;

    /**
     * 发送到默认交换机 AMQP Default
     * <p>
     * 默认交换机实际上是一个由消息代理预先声明好的没有名字（名字为空字符串）的直连交换机（direct exchange）。
     * 它有一个特殊的属性使得它对于简单应用特别有用处：那就是每个新建队列（queue）都会自动绑定到默认交换机上，绑定的路由键（routing key）名称与队列名称相同。
     *
     * @param routingKey 和队列的名称相同
     * @param message
     */
    public void send(String routingKey, Object message) {
        log.info("send default direct routingKey: {} message: {}", routingKey, message);
        template.convertAndSend(routingKey, message);
    }

    /**
     * 发送到direct类型交换机
     *
     * @param exchange
     * @param message
     */
    public void sendDirect(String exchange, String routingKey, Object message) {
        template.convertAndSend(exchange, routingKey, message);
        log.info("send: {} routingKey: {} message: {}", exchange, routingKey, message);
    }


    /**
     * 发送到topic类型的交换机
     *
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendTopic(String exchange, String routingKey, String message) {
        template.convertAndSend(exchange, routingKey, message);
        log.info("send: {} routingKye: {} message: {}", exchange, routingKey, message);
    }

    /**
     * 发送到fanout类型的交换机
     *
     * @param exchange
     * @param message
     */
    public void sendFanout(String exchange, String message) {
        template.convertAndSend(exchange, null, message);
        log.info("send: {} message: {}", exchange, message);
    }


    /**
     * headers类型的交换机(首部模式)
     *
     * @param exchange
     * @param headers
     * @param message
     */
    public void sendHeaders(String exchange, Map<String, Object> headers, String message) {
        MessageProperties properties = new MessageProperties();
        if (!ObjectUtils.isEmpty(headers)) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                properties.setHeader(entry.getKey(), entry.getValue());
            }
        }
        Message data = new Message(message.getBytes(), properties);
        template.convertAndSend(exchange, "", data);
        log.info("send: {} message: {}", exchange, message);
    }

}
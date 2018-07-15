package com.andy.jms.rabbitmq.listener;

import com.andy.jms.rabbitmq.config.AMQPConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MessageReceive {

    //-------------------------普通队列模式-------------------------------
    @RabbitListener(queues = AMQPConstant.QUEUE_A)
    public void receiveQueue(String msg) throws Exception {
        Thread.sleep(6000);
        log.info("收到{}队列的消息是:{}", AMQPConstant.QUEUE_A, msg);
    }

    //-------------------------交换机队列模式-------------------------------
    @RabbitListener(queues = AMQPConstant.TOPIC_QUEUE_A)
    public void receiveTopicA(String msg) throws Exception {
        Thread.sleep(6000);
        log.info("收到{}交换机的消息是:{}", AMQPConstant.TOPIC_EXCHANGE, msg);
    }

    @RabbitListener(queues = AMQPConstant.TOPIC_QUEUE_B)
    public void receiveTopicB(String msg) throws Exception {
        Thread.sleep(6000);
        log.info("收到{}交换机的消息是:{}", AMQPConstant.TOPIC_EXCHANGE, msg);
    }

    //-------------------------headers模式-------------------------------
    @RabbitListener(queues = AMQPConstant.HEADERS_QUEUE)
    public void receiveHeaders(byte[] msg) throws Exception {
        Thread.sleep(6000);
        log.info("收到{}交换机的消息是:{}", AMQPConstant.HEADERS_EXCHANGE, new String(msg));
    }

    //-------------------------xxx模式-------------------------------


}
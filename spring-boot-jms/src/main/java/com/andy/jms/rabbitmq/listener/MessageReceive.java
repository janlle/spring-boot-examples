package com.andy.jms.rabbitmq.listener;

import com.andy.jms.rabbitmq.config.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Leone
 * @since 2018-05-15
 **/
@Slf4j
@Component
public class MessageReceive {


    //-------------------------普通队列模式`-------------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_A)
    public void receiveQueue(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}队列的消息是:{},receiveQueue", RabbitMQConstant.QUEUE_A, msg);
    }


    //-------------------------topic类型的交换机(主题模式)-------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_B)
    public void receiveTopicA(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveTopicA", RabbitMQConstant.TOPIC_EXCHANGE, msg);
    }

    @RabbitListener(queues = RabbitMQConstant.QUEUE_C)
    public void receiveTopicB(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveTopicB", RabbitMQConstant.TOPIC_EXCHANGE, msg);
    }


    //-------------------------headers类型的交换机(首部模式)------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_F)
    public void receiveHeaders(byte[] msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveHeaders", RabbitMQConstant.HEADERS_EXCHANGE, new String(msg));
    }


    //-------------------------direct类型的交换机(直连模式)------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_D)
    public void receiveDirectA(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveDirectA", RabbitMQConstant.DIRECT_EXCHANGE, msg);
    }

    @RabbitListener(queues = RabbitMQConstant.QUEUE_E)
    public void receiveDirectB(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveDirectB", RabbitMQConstant.DIRECT_EXCHANGE, msg);
    }


    //------------------------- fanout类型的交换机(广播模式)-----------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_G)
    public void receiveFanoutA(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveFanoutA", RabbitMQConstant.FANOUT_EXCHANGE, msg);
    }

    @RabbitListener(queues = RabbitMQConstant.QUEUE_H)
    public void receiveFanoutB(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("收到:{}交换机的消息是:{},receiveFanoutB", RabbitMQConstant.FANOUT_EXCHANGE, msg);
    }


}
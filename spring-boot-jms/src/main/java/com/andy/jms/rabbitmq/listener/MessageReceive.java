package com.andy.jms.rabbitmq.listener;

import com.andy.jms.rabbitmq.config.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageReceive {

    //-------------------------普通队列模式-------------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_A)
    public void receiveQueue(String msg) throws Exception {
        Thread.sleep(2000);
        log.info("收到{}队列的消息是:{}", RabbitMQConstant.QUEUE_A, msg);
    }


    //-------------------------topic类型的交换机(主题模式)-------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_B)
    public void receiveTopicA(String msg) throws Exception {
        Thread.sleep(2000);
        log.info("收到{}交换机的消息是:{}", RabbitMQConstant.TOPIC_EXCHANGE, msg);
    }

    @RabbitListener(queues = RabbitMQConstant.QUEUE_C)
    public void receiveTopicB(String msg) throws Exception {
        Thread.sleep(2000);
        log.info("收到{}交换机的消息是:{}", RabbitMQConstant.TOPIC_EXCHANGE, msg);
    }




    //-------------------------headers类型的交换机(首部模式)------------------------
    @RabbitListener(queues = RabbitMQConstant.QUEUE_B)
    public void receiveHeaders(byte[] msg) throws Exception {
        Thread.sleep(6000);
        log.info("收到{}交换机的消息是:{}", RabbitMQConstant.HEADERS_EXCHANGE, new String(msg));
    }




    //-------------------------direct类型的交换机(直连模式)------------------------





    //------------------------- fanout类型的交换机(广播模式)-----------------------
}
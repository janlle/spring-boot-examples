package com.leone.boot.rabbitmq.receive;

import com.leone.boot.rabbitmq.config.RabbitMqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author leone
 * @since 2018-05-15
 **/
@Slf4j
//@Component
public class MessageReceive {


    //-------------------------普通队列模式-------------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_A)
    public void receiveQueue(Object msg, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
        log.info("receive:{} message:{}  --- queue", RabbitMqConstant.QUEUE_A, msg);
    }


    //-------------------------topic类型的交换机(主题模式)-------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_B)
    public void receiveTopicA(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("receive:{} message:{} --- receiveTopicA", RabbitMqConstant.TOPIC_EXCHANGE, msg);
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_C)
    public void receiveTopicB(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("receive:{} message:{} --- receiveTopicB", RabbitMqConstant.TOPIC_EXCHANGE, msg);
    }


    //-------------------------headers类型的交换机(首部模式)------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_F)
    public void receiveHeaders(byte[] msg) throws Exception {
        Thread.sleep(3000);
        log.info("receive:{} message:{} --- receiveHeaders", RabbitMqConstant.HEADERS_EXCHANGE, new String(msg));
    }


    //-------------------------direct类型的交换机(直连模式)------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_D)
    public void receiveDirectA(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("receive:{} message:{} --- receiveDirectA", RabbitMqConstant.DIRECT_EXCHANGE, msg);
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_E)
    public void receiveDirectB(Object msg) throws Exception {
        Thread.sleep(3000);
        log.info("receive:{} message:{} --- receiveDirectB", RabbitMqConstant.DIRECT_EXCHANGE, msg);
    }


    //------------------------- fanout类型的交换机(广播模式)-----------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_G)
    public void receiveFanoutA(Message msg, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {}, message: {}, consumerQueue: {}", msg.getMessageProperties().getReceivedExchange(), new String(msg.getBody()), msg.getMessageProperties().getConsumerQueue());
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_H)
    public void receiveFanoutB(Message msg, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // requeue: true 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {}, message: {}, consumerQueue: {}", msg.getMessageProperties().getReceivedExchange(), new String(msg.getBody()), msg.getMessageProperties().getConsumerQueue());
    }


}
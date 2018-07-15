package com.andy.jms.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息队列配置
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-04-15 18:03
 **/
@Configuration
public class QueueConfig {

    //----------------------普通模式----------------------


    @Bean
    public Queue queueA() {
        return new Queue(AMQPConstant.QUEUE_A, true);
    }

    @Bean
    public Queue queueB() {
        return new Queue(AMQPConstant.QUEUE_B, true);
    }

    @Bean
    public Queue queueC() {
        return new Queue(AMQPConstant.QUEUE_C, true);
    }

    @Bean
    public Queue queueD() {
        return new Queue(AMQPConstant.QUEUE_D, true);
    }

    @Bean
    public Queue queueE() {
        return new Queue(AMQPConstant.QUEUE_E, true);
    }

    @Bean
    public Queue queueF() {
        return new Queue(AMQPConstant.QUEUE_F, true);
    }

}
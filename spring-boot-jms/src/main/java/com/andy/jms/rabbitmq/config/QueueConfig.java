package com.andy.jms.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author: Leone
 * @since: 2018-04-15
 **/
@Configuration
public class QueueConfig {

    @Bean
    public Queue queueA() {
        return new Queue(RabbitMQConstant.QUEUE_A, true);
    }

    @Bean
    public Queue queueB() {
        return new Queue(RabbitMQConstant.QUEUE_B, true);
    }

    @Bean
    public Queue queueC() {
        return new Queue(RabbitMQConstant.QUEUE_C, true);
    }

    @Bean
    public Queue queueD() {
        return new Queue(RabbitMQConstant.QUEUE_D, true);
    }

    @Bean
    public Queue queueE() {
        return new Queue(RabbitMQConstant.QUEUE_E, true);
    }

    @Bean
    public Queue queueF() {
        return new Queue(RabbitMQConstant.QUEUE_F, true);
    }

    @Bean
    public Queue queueG() {
        return new Queue(RabbitMQConstant.QUEUE_G, true);
    }

    @Bean
    public Queue queueH() {
        return new Queue(RabbitMQConstant.QUEUE_H, true);
    }

}
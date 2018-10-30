package com.andy.jms.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author Leone
 * @since 2018-04-15
 **/
@Configuration
public class QueueConfig {

    @Bean
    public Queue queue_a() {
        return new Queue(RabbitMQConstant.QUEUE_A, true);
    }

    @Bean
    public Queue queue_b() {
        return new Queue(RabbitMQConstant.QUEUE_B, true);
    }

    @Bean
    public Queue queue_c() {
        return new Queue(RabbitMQConstant.QUEUE_C, true);
    }

    @Bean
    public Queue queue_d() {
        return new Queue(RabbitMQConstant.QUEUE_D, true);
    }

    @Bean
    public Queue queue_e() {
        return new Queue(RabbitMQConstant.QUEUE_E, true);
    }

    @Bean
    public Queue queue_f() {
        return new Queue(RabbitMQConstant.QUEUE_F, true);
    }

    @Bean
    public Queue queue_g() {
        return new Queue(RabbitMQConstant.QUEUE_G, true);
    }

    @Bean
    public Queue queue_h() {
        return new Queue(RabbitMQConstant.QUEUE_H, true);
    }

    @Bean
    public Queue queue_i() {
        return new Queue(RabbitMQConstant.QUEUE_I, true);
    }

}
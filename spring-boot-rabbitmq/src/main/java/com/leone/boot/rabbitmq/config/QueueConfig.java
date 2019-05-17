package com.leone.boot.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author leone
 * @since 2018-04-15
 **/
@Configuration
public class QueueConfig {


    /**
     * 参数1 name ：队列名
     * 参数2 durable ：是否持久化
     * 参数3 exclusive ：仅创建者可以使用的私有队列，断开后自动删除
     * 参数4 autoDelete : 当所有消费客户端连接断开后，是否自动删除队列
     *
     * @return
     */
    @Bean(name = "queue-a")
    public Queue queue_a() {
        return new Queue(RabbitMqConstant.QUEUE_A, true, false, false);
    }

    @Bean(name = "queue-b")
    public Queue queue_b() {
        return new Queue(RabbitMqConstant.QUEUE_B, true, false, false);
    }

    @Bean(name = "queue-c")
    public Queue queue_c() {
        return new Queue(RabbitMqConstant.QUEUE_C, true, false, false);
    }

    @Bean(name = "queue-d")
    public Queue queue_d() {
        return new Queue(RabbitMqConstant.QUEUE_D, true, false, false);
    }

    @Bean(name = "queue-e")
    public Queue queue_e() {
        return new Queue(RabbitMqConstant.QUEUE_E, true, false, false);
    }

    @Bean(name = "queue-f")
    public Queue queue_f() {
        return new Queue(RabbitMqConstant.QUEUE_F, true, false, false);
    }

    @Bean(name = "queue-g")
    public Queue queue_g() {
        return new Queue(RabbitMqConstant.QUEUE_G, true, false, false);
    }

    @Bean(name = "queue-h")
    public Queue queue_h() {
        return new Queue(RabbitMqConstant.QUEUE_H, true, false, false);
    }

    @Bean(name = "queue-i")
    public Queue queue_i() {
        return new Queue(RabbitMqConstant.QUEUE_I, true, false, false);
    }

}
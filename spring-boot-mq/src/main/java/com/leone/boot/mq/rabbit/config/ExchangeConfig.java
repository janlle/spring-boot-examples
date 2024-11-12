package com.leone.boot.mq.rabbit.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ交换机配置
 *
 * @author leone
 * @since 2018-05-05
 **/
@Configuration
public class ExchangeConfig {


    /**
     * 参数1 name ：交互器名
     * 参数2 durable ：是否持久化
     * 参数3 autoDelete ：当所有消费客户端连接断开后，是否自动删除队列
     * @return
     */

    // ----------------------主题交换机:Topic exchange----------------------
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMqConstant.TOPIC_EXCHANGE, true, false);
    }

    // ----------------------扇形交换机(广播):Fanout ----------------------
    @Bean
    public FanoutExchange fanoutExchange() {
//        return (FanoutExchange) ExchangeBuilder.fanoutExchange(RabbitMqConstant.FANOUT_EXCHANGE).durable(true).build();
        return new FanoutExchange(RabbitMqConstant.FANOUT_EXCHANGE, true, false);
    }

    // ----------------------首部交换机:Headers exchange----------------------
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(RabbitMqConstant.HEADERS_EXCHANGE, true, false);
    }

    // ----------------------直连交换机:Direct exchange----------------------
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMqConstant.DIRECT_EXCHANGE, true, false);
    }

}

package com.andy.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>把队列和交换机绑定
 *
 * @author Leone
 * @since 2018-10-30
 **/
@Configuration
public class BindingConfig {

    // 消息队列

    @Resource(name = RabbitMQConstant.QUEUE_A)
    private Queue queueA;

    @Resource(name = RabbitMQConstant.QUEUE_B)
    private Queue queueB;

    @Resource(name = RabbitMQConstant.QUEUE_C)
    private Queue queueC;

    @Resource(name = RabbitMQConstant.QUEUE_D)
    private Queue queueD;

    @Resource(name = RabbitMQConstant.QUEUE_E)
    private Queue queueE;

    @Resource(name = RabbitMQConstant.QUEUE_F)
    private Queue queueF;

    @Resource(name = RabbitMQConstant.QUEUE_G)
    private Queue queueG;

    @Resource(name = RabbitMQConstant.QUEUE_H)
    private Queue queueH;

    @Resource(name = RabbitMQConstant.QUEUE_I)
    private Queue queueI;

    // 交换机

    @Resource
    private TopicExchange topicExchange;

    @Resource
    private FanoutExchange fanoutExchange;

    @Resource
    private HeadersExchange headersExchange;

    @Resource
    private DirectExchange directExchange;


    // ----------------------主题交换机:Topic exchange----------------------
    // 把队列和交换机绑定 *表示一个词,#表示任意数量（零个或多个）单词。
    @Bean
    public Binding topicBindingA() {
        return BindingBuilder.bind(queueB).to(topicExchange).with(RabbitMQConstant.KEY_A);
    }

    @Bean
    public Binding topicBindingB() {
        return BindingBuilder.bind(queueC).to(topicExchange).with(RabbitMQConstant.KEY_B);
    }


    // ----------------------扇形交换机(广播):Fanout ----------------------
    @Bean
    public Binding fanoutBindingA() {
        return BindingBuilder.bind(queueG).to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingB() {
        return BindingBuilder.bind(queueH).to(fanoutExchange);
    }


    // ----------------------首部交换机:Headers exchange----------------------
    @Bean
    public Binding headersBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "value-a");
        map.put("header-b", "value-b");
        return BindingBuilder.bind(queueF).to(headersExchange).whereAll(map).match();
    }

    // ----------------------直连交换机:Direct exchange----------------------
    @Bean
    public Binding directBindingA() {
        return BindingBuilder.bind(queueD).to(directExchange).with(RabbitMQConstant.KEY_C);
    }

    @Bean
    public Binding directBindingB() {
        return BindingBuilder.bind(queueE).to(directExchange).with(RabbitMQConstant.KEY_D);
    }


}

package com.andy.jms.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * rabbitMQ交换机配置
 *
 * @Author: Mr.lyon
 * @CreateBy: 2018-07-15 18:00
 **/

@Configuration
public class ExchangeConfig {

    @Resource(name = "queueA")
    private Queue queueA;

    @Resource(name = "queueB")
    private Queue queueB;

    @Resource(name = "queueC")
    private Queue queueC;

    @Resource(name = "queueD")
    private Queue queueD;

    @Resource(name = "queueE")
    private Queue queueE;

    @Resource(name = "queueF")
    private Queue queueF;


    //----------------------主题交换机:Topic exchange----------------------
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE);
    }

    //把队列和交换机绑定 *表示一个词,#表示零个或多个词
    @Bean
    public Binding topicBindingA() {
        return BindingBuilder.bind(queueA).to(topicExchange()).with("topic.message");
    }

    @Bean
    public Binding topicBindingB() {
        return BindingBuilder.bind(queueB).to(topicExchange()).with("topic.#");
    }



    //----------------------扇形交换机(广播):Fanout exchangeFanout----------------------
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.FANOUT_EXCHANGE);
    }
    //把队列和交换机绑定
    @Bean
    public Binding fanoutBindingA() {
        return BindingBuilder.bind(queueC).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingB() {
        return BindingBuilder.bind(queueD).to(fanoutExchange());
    }



    //----------------------首部交换机:Headers exchange----------------------
    @Bean
    public Queue headersQueue() {
        return new Queue(RabbitMQConstant.QUEUE_A);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(RabbitMQConstant.HEADERS_EXCHANGE);
    }
    //把队列和交换机绑定
    @Bean
    public Binding headersBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("headera", "valuea");
        map.put("headerb", "valueb");
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }


    //----------------------直连交换机:Direct exchange----------------------

}

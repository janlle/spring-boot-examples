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
 * @author: lyon
 * @since: 2018-05-5
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

    @Resource(name = "queueG")
    private Queue queueG;

    @Resource(name = "queueH")
    private Queue queueH;


    // ----------------------主题交换机:Topic exchange----------------------
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE);
    }

    // 把队列和交换机绑定 *表示一个词,#表示任意数量（零个或多个）单词。
    @Bean
    public Binding topicBindingA() {
        return BindingBuilder.bind(queueB).to(topicExchange()).with(RabbitMQConstant.KEY_A);
    }

    @Bean
    public Binding topicBindingB() {
        return BindingBuilder.bind(queueC).to(topicExchange()).with(RabbitMQConstant.KEY_B);
    }


    // ----------------------扇形交换机(广播):Fanout ----------------------
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.FANOUT_EXCHANGE);
    }

    // 把队列和交换机绑定
    @Bean
    public Binding fanoutBindingA() {
        return BindingBuilder.bind(queueG).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingB() {
        return BindingBuilder.bind(queueH).to(fanoutExchange());
    }


    // ----------------------首部交换机:Headers exchange----------------------
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(RabbitMQConstant.HEADERS_EXCHANGE);
    }

    // 把队列和交换机绑定
    @Bean
    public Binding headersBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "value-a");
        map.put("header-b", "value-b");
        return BindingBuilder.bind(queueF).to(headersExchange()).whereAll(map).match();
    }


    // ----------------------直连交换机:Direct exchange----------------------
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstant.DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBindingA() {
        return BindingBuilder.bind(queueD).to(directExchange()).with(RabbitMQConstant.KEY_C);
    }

    @Bean
    public Binding directBindingB() {
        return BindingBuilder.bind(queueE).to(directExchange()).with(RabbitMQConstant.KEY_D);
    }

}

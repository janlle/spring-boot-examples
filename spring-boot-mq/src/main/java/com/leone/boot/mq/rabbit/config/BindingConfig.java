package com.leone.boot.mq.rabbit.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>把队列和交换机绑定
 *
 * @author leone
 * @since 2018-10-30
 **/
@Configuration
public class BindingConfig {

    // 消息队列
    @Resource(name = RabbitMqConstant.QUEUE_B)
    private Queue queue_b;

    @Resource(name = RabbitMqConstant.QUEUE_C)
    private Queue queue_c;

    @Resource(name = RabbitMqConstant.QUEUE_D)
    private Queue queue_d;

    @Resource(name = RabbitMqConstant.QUEUE_E)
    private Queue queue_e;

    @Resource(name = RabbitMqConstant.QUEUE_F)
    private Queue queue_f;

    @Resource(name = RabbitMqConstant.QUEUE_G)
    private Queue queue_g;

    @Resource(name = RabbitMqConstant.QUEUE_H)
    private Queue queue_h;

    @Resource(name = RabbitMqConstant.QUEUE_I)
    private Queue queue_i;

    // 交换机
    @Resource
    private TopicExchange topicExchange;

    @Resource
    private FanoutExchange fanoutExchange;

    @Resource
    private HeadersExchange headersExchange;

    @Resource
    private DirectExchange directExchange;


    /**
     * 主题交换机:Topic exchange
     * <p>
     * 发送到主题交换机上的消息需要携带指定规则的routing_key，主题交换机会根据这个规则将数据路由到对应的一个或多个队列上。
     * 主题交换机的routing_key需要有一定的规则，交换机和队列的binding_key需要采用*.#.*.....的格式，每个部分用.分开，其中：
     * [*]表示一个词,[#]表示任意数量（零个或多个）单词。
     *
     * @return
     */
    @Bean
    public Binding topicBindingQueueB() {
        return BindingBuilder.bind(queue_b).to(topicExchange).with(RabbitMqConstant.ROUTING_KEY_A);
    }

    @Bean
    public Binding topicBindingQueueC() {
        return BindingBuilder.bind(queue_c).to(topicExchange).with(RabbitMqConstant.ROUTING_KEY_B);
    }


    /**
     * 直连交换机:Direct exchange
     * 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。
     * 如果一个队列绑定到该交换机上要求路由键 “abc”，则只有被标记为“abc”的消息才被转发，不会转发abc.def，也不会转发dog.ghi，只会转发abc。
     *
     * @return
     */
    @Bean
    public Binding directBindingQueueD() {
        return BindingBuilder.bind(queue_d).to(directExchange).with(RabbitMqConstant.ROUTING_KEY_C);
    }

    @Bean
    public Binding directBindingQueueE() {
        return BindingBuilder.bind(queue_e).to(directExchange).with(RabbitMqConstant.ROUTING_KEY_D);
    }


    /**
     * 首部交换机:Headers exchange
     * 交换机绑定队列的时候需定义一个Hash的数据结构，消息发送的时候，会携带一组hash类型的数据，当Hash的内容匹配上的时候，消息就会被写入队列。
     * 绑定规则中Hash结构中要求携带一个key: "x-match", value可以是 any: 仅匹配一个，或者 all: 全部匹配。不处理routingKey
     * all: 默认值。一个传送消息的header里的键值对和交换机的header键值对全部匹配，才可以路由到对应交换机（whereAll）
     * any: 一个传送消息的header里的键值对和交换机的header键值对任意一个匹配，就可以路由到对应交换机（whereAny）
     *
     * @return
     */
    @Bean
    public Binding headersBindingQueueF() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "value-a");
        map.put("header-b", "value-b");
        return BindingBuilder.bind(queue_f).to(headersExchange).whereAll(map).match();
    }

    @Bean
    public Binding headersBindingQueueI() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "value-a");
        return BindingBuilder.bind(queue_i).to(headersExchange).whereAny(map).match();
    }


    /**
     * 扇形交换机(广播):Fanout
     * 扇形交换机是最基本的交换机类型，它所能做的事情非常简单———广播消息。扇形交换机会把能接收到的消息全部发送给绑定在自己身上的队列。
     * 因为广播不需要“思考”，所以扇形交换机处理消息的速度也是所有的交换机类型里面最快的。不处理routingKey
     *
     * @return
     */
    @Bean
    public Binding fanoutBindingQueueG() {
        return BindingBuilder.bind(queue_g).to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingQueueH() {
        return BindingBuilder.bind(queue_h).to(fanoutExchange);
    }


}

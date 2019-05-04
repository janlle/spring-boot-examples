package com.leone.rabbitmq.config;

/**
 * @author leone
 * @since 2018-05-01
 **/
public interface RabbitMQConstant {

    // 消息队列配置
    String QUEUE_A = "queue_a";
    String QUEUE_B = "queue_b";
    String QUEUE_C = "queue_c";
    String QUEUE_D = "queue_d";
    String QUEUE_E = "queue_e";
    String QUEUE_F = "queue_f";
    String QUEUE_G = "queue_g";
    String QUEUE_H = "queue_h";
    String QUEUE_I = "queue_i";


    // rabbitMQ有四种类型的交换机fanout、direct、topic、headers
    String HEADERS_EXCHANGE = "headers_exchange";
    String FANOUT_EXCHANGE = "fanout_exchange";
    String DIRECT_EXCHANGE = "direct_exchange";
    String TOPIC_EXCHANGE = "topic_exchange";


    // 绑定匹配规则[*]表示一个单词,[#]表示任意数量（零个或多个）单词。
    String KEY_A = "topic.#";
    String KEY_B = "topic.*";
    String KEY_C = "direct_a";
    String KEY_D = "direct_b";

}

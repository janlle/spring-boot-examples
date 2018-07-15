package com.andy.jms.rabbitmq.config;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-01 15:30
 **/
public interface RabbitMQConstant {

    // 消息队列配置
    String QUEUE_A = "queue-a";
    String QUEUE_B = "queue-b";
    String QUEUE_C = "queue-c";
    String QUEUE_D = "queue-d";
    String QUEUE_E = "queue-e";
    String QUEUE_F = "queue-f";


    // rabbitMQ有四种类型的交换机fanout、direct、topic、headers
    String HEADERS_EXCHANGE = "headers-exchange";
    String FANOUT_EXCHANGE = "fanout-exchange";
    String DIRECT_EXCHANGE = "direct-exchange";
    String TOPIC_EXCHANGE = "topic-exchange";


    // 绑定匹配规则*表示一个词,#表示任意数量（零个或多个）单词。
    String KEY_A = "topic.#";
    String KEY_B = "topic.msg";

}

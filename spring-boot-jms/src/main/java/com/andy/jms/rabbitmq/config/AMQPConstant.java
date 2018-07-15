package com.andy.jms.rabbitmq.config;

/**
 * @author: Mr.ruoLin
 * @createBy: 2018-05-01 15:30
 **/
public interface AMQPConstant {

    // 普通消息队列
    String QUEUE_A = "queue-a";
    String QUEUE_B = "queue-b";

    // headers 消息队列
    String HEADERS_QUEUE = "headers-queue";

    // topic消息队列
    String TOPIC_QUEUE_A = "topic-queue-A";
    String TOPIC_QUEUE_B = "topic-queue-B";

    // rabbitMq有四种类型的交换机fanout、direct、topic、headers
    String FANOUT_EXCHANGE = "fanout-exchange";
    String DIRECT_EXCHANGE = "direct-exchange";
    String TOPIC_EXCHANGE = "topic-exchange";
    String HEADERS_EXCHANGE = "headers-exchange";

    // 绑定匹配规则
    String KEY_A = "topic.message";
    String KEY_B = "topic.msg";

}

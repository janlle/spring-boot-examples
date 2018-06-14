package com.andy.jms.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConf {

     //----------------------普通模式----------------------
     @Bean
     public Queue queue() {
          return new Queue(AMQPConstant.QUEUE_A);
     }
     //----------------------topic模式 交换机----------------------
     @Bean
     public Queue topicQueueA() {
          return new Queue(AMQPConstant.TOPIC_QUEUE_A, true);
     }

     @Bean
     public Queue topicQueueB() {
          return new Queue(AMQPConstant.TOPIC_QUEUE_B, true);
     }

     //交换机
     @Bean
     public TopicExchange topicExchange() {
          return new TopicExchange(AMQPConstant.TOPIC_EXCHANGE);
     }

     //把队列和交换机绑定 *表示一个词,#表示零个或多个词
     @Bean
     public Binding topicBindingA() {
          return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with("topic.message");
     }

     @Bean
     public Binding topicBindingB() {
          return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with("topic.#");
     }

     //----------------------Fanout(广播)模式 交换机----------------------
     @Bean
     public FanoutExchange fanoutExchange() {
          return new FanoutExchange(AMQPConstant.FANOUT_EXCHANGE);
     }
     //把队列和交换机绑定
     @Bean
     public Binding fanoutBindingA() {
          return BindingBuilder.bind(topicQueueA()).to(fanoutExchange());
     }

     @Bean
     public Binding fanoutBindingB() {
          return BindingBuilder.bind(topicQueueB()).to(fanoutExchange());
     }


     //----------------------Header模式 交换机----------------------
     @Bean
     public Queue headersQueue() {
          return new Queue(AMQPConstant.HEADERS_QUEUE);
     }

     @Bean
     public HeadersExchange headersExchange() {
          return new HeadersExchange(AMQPConstant.HEADERS_EXCHANGE);
     }
     //把队列和交换机绑定
     @Bean
     public Binding headersBinding() {
          Map<String, Object> map = new HashMap<>();
          map.put("headera", "valuea");
          map.put("headerb", "valueb");
          return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
     }

     //----------------------topic模式 交换机----------------------

}
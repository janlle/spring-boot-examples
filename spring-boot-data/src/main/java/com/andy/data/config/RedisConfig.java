package com.andy.data.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;

/**
 * <p> redis配置
 *
 * @author: Leone
 * @since: 2018-07-19
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

//    @Bean
//    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//                                            MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
//
//    @Bean
//    public Receiver receiver(CountDownLatch latch) {
//        return new Receiver(latch);
//    }
//
//    @Bean
//    public CountDownLatch latch() {
//        return new CountDownLatch(1);
//    }
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
//        return new StringRedisTemplate(connectionFactory);
//    }
//
//    public class Receiver {
//
//        private CountDownLatch latch;
//
//        @Autowired
//        public Receiver(CountDownLatch latch) {
//            this.latch = latch;
//        }
//
//        public void receiveMessage(String message) {
//            latch.countDown();
//        }
//    }

}

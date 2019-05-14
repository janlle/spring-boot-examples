package com.leone.boot.log.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@Slf4j
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * @param topic
     * @param content
     */
    public void send(String topic, String content) {
        kafkaTemplate.send(topic, content);
        log.info("send to topic: {} success...", topic);
    }
}

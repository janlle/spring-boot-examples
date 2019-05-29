package com.leone.boot.log.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @param topic
     * @param content
     */
    public void send(String topic, Object content) {
        String data = null;
        try {
            data = objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, data);
        log.info("send to topic: {} success...", topic);
    }

}

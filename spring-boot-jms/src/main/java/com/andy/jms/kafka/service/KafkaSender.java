package com.andy.jms.kafka.service;

import com.andy.jms.kafka.commen.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *
     * @param topic
     * @param body
     */
    public void send(String topic, Object body) {
        Message<String> message = new Message<>();
        message.setId(System.currentTimeMillis());
        message.setMessage(body.toString());
        message.setTime(new Date());
        String content = null;
        try {
            content = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, content);
        log.info("send {} to {} success!", message, topic);
    }
}

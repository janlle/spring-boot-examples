package com.leone.boot.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leone.boot.common.utils.RandomValue;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@Component
public class KafkaSender {

    private static final Logger log = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private Long offset = 0L;

    /**
     * @param topic
     * @param count
     */
    public String send(String topic, Integer count) throws Exception {
        for (int i = 0; i < count; i++) {
            // Message<Map> message = new Message<>(offset, RandomValue.randomUser(), new Date());
            String message = RandomValue.randomMessage();
            offset++;
            CompletableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, message);
            Thread.sleep(10);
            RecordMetadata s = send.get().getRecordMetadata();
            System.out.println("hasOffset " + s.hasOffset() + " partition " + s.partition() + " offset " + s.offset());
            log.info("send to: {} message: {} ", topic, message);
        }
        return "send to " + topic + " count: " + count;
    }

}

package com.leone.boot.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@Slf4j
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"spring-boot-topic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("record: {} message: {}", record, message);
        }
    }

}

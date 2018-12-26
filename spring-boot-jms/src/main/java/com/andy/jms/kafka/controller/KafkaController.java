package com.andy.jms.kafka.controller;

import com.andy.jms.kafka.service.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 
 *
 * @author leone
 * @since 2018-12-26
 **/
@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("/kafka/{topic}")
    public String send(@PathVariable("topic") String topic, @RequestParam String message) {
        kafkaSender.send(topic, message);
        return "success";
    }

}
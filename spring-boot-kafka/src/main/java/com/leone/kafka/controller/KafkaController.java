package com.leone.kafka.controller;

import com.leone.kafka.service.KafkaSender;
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
@RestController
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("/api/kafka/{topic}")
    public String send(@PathVariable("topic") String topic, @RequestParam Integer count) throws Exception {
        return kafkaSender.send(topic, count);
    }

}
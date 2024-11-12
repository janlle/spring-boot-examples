package com.leone.boot.mq.controller;

import com.leone.boot.mq.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping("/send/{topic}")
    public String send(@PathVariable("topic") String topic, @RequestParam Integer count) throws Exception {
        return kafkaSender.send(topic, count);
    }

}
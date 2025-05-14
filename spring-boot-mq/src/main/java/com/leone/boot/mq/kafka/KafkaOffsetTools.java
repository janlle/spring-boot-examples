package com.leone.boot.mq.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class KafkaOffsetTools {

    public static void main(String[] args) throws InterruptedException {

        KafkaConsumer<String, String> consumer = getKafkaConsumer("g1", "test-topic", "cloud:9092");
        List<PartitionInfo> partitionInfoList = consumer.partitionsFor("test-topic");

        partitionInfoList.forEach(e -> {
            TopicPartition topicPartition = new TopicPartition(e.topic(), e.partition());
            consumer.assign(Collections.singletonList(topicPartition));
            consumer.seekToEnd(Collections.singletonList(topicPartition));
            long position = consumer.position(topicPartition);
            System.err.println(e + " " + position);
        });

    }

    /**
     * 获取Kafka消费者实例
     *
     * @param group   消费者组
     * @param topic   主题名
     * @param servers 服务器列表
     * @return KafkaConsumer<String, String>
     */
    private static KafkaConsumer<String, String> getKafkaConsumer(String group, String topic, String servers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", group);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("max.poll.records", 100);
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        return new KafkaConsumer<>(props);

    }


}



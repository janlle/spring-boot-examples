package com.leone.boot.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2022-01-27
 **/
public class TimestampOffsetConsumer {

    public static void main(String[] args) {
        LocalDateTime fetchDataTime = LocalDateTime.of(2022, 5, 4, 0, 0, 0);

        String topic = "usage_record";

        try (KafkaConsumer<String, String> consumer = KafkaUtil.newConsumer("10.225.2.16:9092", "g14", topic)) {

            List<PartitionInfo> partitionInfo = consumer.partitionsFor(topic);
            List<TopicPartition> topicPartitions = new ArrayList<>();
            Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();

            System.err.println("开始消费时间: " + fetchDataTime);

            for (PartitionInfo info : partitionInfo) {
                topicPartitions.add(new TopicPartition(info.topic(), info.partition()));
                timestampsToSearch.put(new TopicPartition(info.topic(), info.partition()), fetchDataTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }

            consumer.assign(topicPartitions);

            // 获取每个partition一个小时之前的偏移量
            Map<TopicPartition, OffsetAndTimestamp> map = consumer.offsetsForTimes(timestampsToSearch);

            OffsetAndTimestamp offsetTimestamp;
            System.out.println("开始设置各分区初始偏移量...");
            for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : map.entrySet()) {
                // 如果设置的查询偏移量的时间点大于最大的索引记录时间，那么value就为空
                offsetTimestamp = entry.getValue();
                if (offsetTimestamp != null) {
                    System.out.println("partition = " + entry.getKey().partition() + ", time = " + offsetTimestamp.timestamp() + ", offset = " + offsetTimestamp.offset());
                    // 设置读取消息的偏移量
                    consumer.seek(entry.getKey(), offsetTimestamp.offset());
                }
            }

            System.out.println("设置各分区初始偏移量结束...");

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.leone.boot.mq.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
public class JavaKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JavaKafkaConsumer.class);
    private static final Properties params = new Properties();

    static {
        // 集群信息
        params.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "10.225.2.16:9092");

        // 组id
        params.put(ConsumerConfig.GROUP_ID_CONFIG, "g2");

        // 指定k-v反序列化规则
        params.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        params.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // earliest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        // latest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        // none topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        params.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // 设置拉取数量
        params.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);

        // 自动提交offset
        params.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        // 提交offset的时间，单位ms，即1秒钟提交一次
        params.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);

    }

    public static void main(String[] args) throws InterruptedException {
        autoCommitOffset();
    }

    public static void autoCommitOffset() throws InterruptedException {
        // 自动提交offset
        params.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 提交offset的时间，单位ms，即1秒钟提交一次
        params.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        // 创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(params);
        // 订阅主题
        consumer.subscribe(Collections.singletonList("bigdata_order_topic"));

        while (true) {
            TimeUnit.SECONDS.sleep(1);
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("receive key: {} partition: {} offset: {} count: {} value: {}",
                        record.key(), record.partition(), record.offset(), records.count(), record.value());
            }
        }

    }

    public static void manualCommitOffset() throws InterruptedException {
        // 手动提交offset
        params.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(params);
        // 订阅主题
        consumer.subscribe(Collections.singletonList("test-topic"));
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("receive key: {} partition: {} offset: {} count: {} value: {}",
                        record.key(), record.partition(), record.offset(), records.count(), record.value());
                // 自动提交offset
                consumer.commitSync(Duration.ofSeconds(1));
            }
        }
    }



}

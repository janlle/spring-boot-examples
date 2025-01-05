package com.leone.boot.mq.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
public class JavaKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(JavaKafkaProducer.class);

    private static final Producer<String, String> producer;

    private static final Map<String, Object> params = new HashMap<>();

    static {
        // producer 参数
        params.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "10.225.2.16:9092");
        params.put(CommonClientConfigs.RETRY_BACKOFF_MS_CONFIG, 1000);
        params.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        params.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 多少数据打包为一个Batch
        params.put(ProducerConfig.BATCH_SIZE_CONFIG, "1M");

        // 如果一个Batch迟迟无法凑满，超过linger.ms的时间，不管这个Batch有没有写满，都必须发送出去了。
        params.put(ProducerConfig.LINGER_MS_CONFIG, "200ms");
        // ack 机制
        params.put(ProducerConfig.ACKS_CONFIG, "1");

        // 最大请求大小
        params.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760);

        // 重试机制
        params.put(ProducerConfig.RETRIES_CONFIG, 5);

        // 内存缓冲的大小
        params.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 67108864);

        // 生产者对象
        producer = new KafkaProducer<>(params);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            TimeUnit.MILLISECONDS.sleep(5000);
            String uuid = UUID.randomUUID().toString() + LocalDateTime.now();
            Future<RecordMetadata> send = producer.send(new ProducerRecord<>("test", Integer.toString(i), uuid));
            logger.info("send key: {} value: {} partition: {}", i, uuid, send.get().partition());
        }
        producer.close();
    }

}

package com.leone.boot.mq.kafka;


import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-17
 **/
public class KafkaClientTest {

    private final static String TOPIC = "bigdata_second_topic";

    public static AdminClient adminClient;

    public void init() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.225.2.16:9092");
        adminClient = AdminClient.create(properties);
    }


    /**
     * 创建topic
     */
    public void createTopic() throws Exception {
        List<NewTopic> newTopics = Collections.singletonList(new NewTopic(TOPIC, 3, (short) 1));
        CreateTopicsResult result = adminClient.createTopics(newTopics);
        System.out.println(result.all().get());
    }

    /**
     * 查看topic详情
     */
    public void descTopic() throws Exception {
        // DescribeTopicsResult result = adminClient.describeTopics(Collections.singletonList(TOPIC));
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList("bigdata_order_topic", "bigdata_monitor_topic", "usage_record", "bigdata_second_topic_v1"));
        KafkaFuture<Map<String, TopicDescription>> allInfo = result.all();
        // 获取 topic 所有属性
        Map<String, TopicDescription> topicDescriptionMap = allInfo.get();
        topicDescriptionMap.forEach((k, v) -> System.out.println(k + "\t\t partition: " + v.partitions().size()));
    }

    /**
     * 查看topic列表
     */
    public void listTopic() throws Exception {
        ListTopicsResult result = adminClient.listTopics();
        Set<String> topics = result.names().get();
        result.listings().get().forEach(e -> System.out.println(e.name()));
    }


    /**
     * 查看 consumer 的组
     */
    public void listGroups() throws Exception {
        adminClient.listConsumerGroups().all().get().forEach(System.out::println);
        DescribeClusterResult result = adminClient.describeCluster();
    }

    /**
     * 查看集群详情
     */
    public void descCluster() throws Exception {
        DescribeClusterResult result = adminClient.describeCluster();
        result.nodes().get().forEach(System.out::println);
        System.out.println(result.clusterId().get());
        System.out.println(result.controller().get());
    }

    /**
     * 删除topic
     */
    public void deleteTopic() {
        DeleteTopicsResult topicsResult = adminClient.deleteTopics(Collections.singletonList("test"));
    }

}

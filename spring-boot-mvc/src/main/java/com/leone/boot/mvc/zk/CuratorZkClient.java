package com.leone.boot.mvc.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-12
 **/
public class CuratorZkClient {

    private final static Logger logger = LoggerFactory.getLogger(ZkClient.class);

    private static final CuratorFramework client;

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 服务器列表 重试策略 会话超时时间，单位毫秒，默认60000ms 连接创建超时时间，单位毫秒，默认60000ms
        // client = CuratorFrameworkFactory.newClient(
        //        "localhost:2181,
        //        5000,
        //        3000,
        //        retryPolicy);

        // fluent api 创建 client
        client = CuratorFrameworkFactory.builder()
                .connectString("vm.rm:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
    }

    public static void main(String[] args) throws Exception {
        client.start();
        create("/aaa", "a");
    }

    /**
     * 创建一个节点，指定创建模式，附带初始化内容：
     *
     * @param path path
     * @param data data
     * @throws Exception ex
     */
    private static void create(String path, String data) throws Exception {
        String s = client.create().withMode(CreateMode.PERSISTENT).forPath(path, data.getBytes());
        logger.info("create: {} ", s);
    }

    /**
     * 更新一个节点的数据内容
     *
     * @param path    path
     * @param content current
     * @throws Exception ex
     */
    private static void setData(String path, String content) throws Exception {
        Stat stat = client.setData().forPath(path, content.getBytes());
        logger.info("setData: {}", stat);
    }


    /**
     * 强制保证删除，并且递归删除其所有的子节点
     *
     * @param path path
     * @throws Exception ex
     */
    private static void delete(String path) throws Exception {
        Void v = client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        logger.info("delete: {}", v);
    }


    /**
     * 读取一个节点的数据内容，同时获取到该节点的 stat
     *
     * @param path path
     * @throws Exception ex
     */
    private static void getData(String path) throws Exception {
        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
        logger.info("getData: {}", new String(bytes));
    }


    /**
     * 该方法返回一个Stat实例
     *
     * @param path path
     * @throws Exception ex
     */
    private static void exists(String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        logger.info("checkPath: {} ", stat);
    }

    /**
     * @param path path
     * @throws Exception ex
     */
    private static void getChildren(String path) throws Exception {
        List<String> children = client.getChildren().forPath(path);
        for (String child : children) {
            logger.info("getChildren: {}", child);
        }
    }

}

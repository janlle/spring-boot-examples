package com.andy.mvc.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-07 22:44
 **/
public class ZkTest {

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("120.78.231.235:2181", 5000, null);
        List<String> list = zk.getChildren("/", null);
        for (String s : list) {
            System.out.println(s);
        }

    }

}

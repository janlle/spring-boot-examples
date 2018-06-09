package com.andy.mvc.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-07 22:44
 **/
@Slf4j
public class ZkTest {

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("120.78.231.235:2181,120.76.77.230:2181,120.76.101.243:2181", 5000, new Watcher(){
            @Override
            public void process(WatchedEvent event) {
                log.debug(" receive event : "+event.getType().name());
            }
        });
        List<String> list = zk.getChildren("/", null);

        for (String s : list) {
            System.out.println(s);
        }

    }

}

package com.leone.boot.mvc.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ZooKeeper -server host:port cmd args
 * stat path [watch]
 * set path data [version]
 * ls path [watch]
 * delquota [-n|-b] path
 * ls2 path [watch]
 * setAcl path acl
 * setquota -n|-b val path
 * history
 * redo cmdno
 * printwatches on|off
 * delete path [version]
 * sync path
 * listquota path
 * rmr path
 * get path [watch]
 * create [-s] [-e] path data acl
 * addauth scheme auth
 * quit
 * getAcl path
 * close
 * connect host:port
 *
 * @author leone
 * @since 2018-06-16
 **/
public class ZkClient {

    private final static Logger logger = LoggerFactory.getLogger(ZkClient.class);

    private static ZooKeeper zooKeeper;

    static {
        try {
            zooKeeper = new ZooKeeper("vm.rm:2181", 5000, (event) -> {
                Watcher.Event.KeeperState state = event.getState();
                if (state == Watcher.Event.KeeperState.SyncConnected) {
                    logger.info("Create zookeeper successful...");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // create("/111/333", "1");
        getChildren("/");
    }

    /**
     * PERSISTENT：              永久节点
     * EPHEMERAL：               临时节点
     * PERSISTENT_SEQUENTIAL：   永久顺序节点
     * EPHEMERAL_SEQUENTIAL：    临时顺序节点
     *
     * @param path path
     * @param data data
     * @throws Exception ex
     */
    public static void create(String path, String data) throws Exception {
        // 1.节点的路径 2.节点数据 3:节点的权限 4:节点的类型
        String s = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info("Create node: {}", s);
    }


    /**
     * @param path path
     * @param data data
     * @throws Exception ex
     */
    public static void setData(String path, String data) throws Exception {
        // 1.节点的路径 2.数据 3.版本
        zooKeeper.setData(path, data.getBytes(), -1);
        logger.info("SetData path: {} data: {}", path, data);
    }


    /**
     * @param path path
     * @throws Exception ex
     */
    public static void delete(String path) throws Exception {
        // 1.节点的路径 2.版本 -1 表示所有版本
        zooKeeper.delete(path, -1);
        logger.info("Delete: {}", path);
    }


    /**
     * @param path path
     * @throws Exception ex
     */
    public static void getData(String path) throws Exception {
        byte[] data = zooKeeper.getData(path, false, null);
        logger.info("getData: {}", new String(data));
    }

    /**
     * @param path path
     * @throws Exception ex
     */
    public static void exists(String path) throws Exception {
        // 1.节点的路径 2.是否需要监听
        Stat stat = zooKeeper.exists(path, false);
        logger.info("Exists: {} {}", path, stat);
    }

    /**
     * 获取子节点
     *
     * @param path path
     * @throws Exception ex
     */
    public static void getChildren(String path) throws Exception {
        List<String> children = zooKeeper.getChildren(path, false);
        children.forEach(e -> logger.info("GetChildren: {}", e));
    }

}

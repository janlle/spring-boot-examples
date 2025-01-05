package com.leone.boot.mvc.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-30
 **/
public class ZkLock {

    private final static Logger LOG = LoggerFactory.getLogger(ZkLock.class);

    private static final String LOCK_ROOT_PATH = "/locks";

    private static final String LOCK_NODE_NAME = "/lock_";

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    private String lockPath;

    // 监控lockPath的前一个节点的watcher
    private final Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            LOG.info(event.toString());
            synchronized (this) {
                notifyAll();
            }
        }
    };

    static {
        try {
            // 初始化连接
            zooKeeper = new ZooKeeper("localhost:2181", 10000, event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    LOG.info("Connection zookeeper successful... {}", event.getState());
                    COUNT_DOWN_LATCH.countDown();
                }
            });
            // 创建永久锁节点
            Stat stat = zooKeeper.exists(LOCK_ROOT_PATH, false);
            if (stat == null) {
                zooKeeper.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        COUNT_DOWN_LATCH.await();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int a = i;
            executorService.execute(() -> {
                ZkLock lock = new ZkLock();
                try {
                    lock.acquireLock();
                    process(a);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.releaseLock();
                }
            });
        }
        executorService.shutdown();
    }

    /**
     * 业务逻辑
     */
    private static void process(int i) throws InterruptedException {
        LOG.info("process start " + i + "----------");
        Thread.sleep(3000);
        LOG.info("process end " + i + "----------");
    }

    /**
     * 加锁(先创建节点，判断是否是最小的节点，如果是就获取到了锁)
     */
    public void acquireLock() throws InterruptedException, KeeperException {
        // 创建顺序零时节点 /lock/l_15635252328350000000072
        lockPath = zooKeeper.create(LOCK_ROOT_PATH + LOCK_NODE_NAME, Thread.currentThread().getName().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        LOG.info("Create lock: {}", lockPath);
        // 尝试获取锁
        attemptLock();
    }


    /**
     * 释放锁
     */
    public void releaseLock() {
        try {
            zooKeeper.delete(lockPath, -1);
            LOG.info(Thread.currentThread().getName() + " unlock: {}", lockPath);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     */
    private void attemptLock() throws KeeperException, InterruptedException {
        // 获取/locks下所有子节点 [lock_0000000030, lock_0000000031, lock_0000000032]
        List<String> locks = zooKeeper.getChildren(LOCK_ROOT_PATH, false);

        // 把获取到的节点排序
        Collections.sort(locks);

        // 获取当前锁在 list 的下标：例如获取 lock_0000000032 在 list 中的下标
        int index = locks.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));
        System.out.println(index);

        // 如果 lock 是序号最小的节点，则获取锁
        if (index == 0) {
            LOG.info("{} GetLock: {}", Thread.currentThread().getName(), lockPath);
        } else if (index > 0) {
            // 获取前一个 lock，监控这个节点
            String preLock = locks.get(index - 1);
            Stat stat = zooKeeper.exists(LOCK_ROOT_PATH + "/" + preLock, watcher);
            // 假如前一个节点不存在了，比如说执行完毕，或者执行节点掉线，重新获取锁
            if (stat == null) {
                attemptLock();
                // 阻塞当前进程，直到preLockPath释放锁，被watcher观察到，notifyAll后，重新acquireLock
            } else {
                LOG.info("wait preLock unlock path: " + preLock);
                synchronized (watcher) {
                    watcher.wait();
                }
                attemptLock();
            }
        }
    }


}

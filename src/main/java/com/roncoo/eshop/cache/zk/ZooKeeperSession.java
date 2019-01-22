package com.roncoo.eshop.cache.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: tianyu
 * @Date: 2019/1/10 22:18
 * @Description:
 */
@Component
@Slf4j
public class ZooKeeperSession {

    @Value("${kafka.consumer.zookeeper.connect}")
    private static String zookeeper_cluster;

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zookeeper;

    public ZooKeeperSession() {
        // 去连接zookeeper server，创建会话的时候，是异步去进行的
        // 所以要给一个监听器，说告诉我们什么时候才是真正完成了跟zk server的连接
        try {
            this.zookeeper = new ZooKeeper(zookeeper_cluster, 50000, new ZooKeeperWatcher());
            // 给一个状态CONNECTING，连接中
            System.out.println(zookeeper.getState());

            try {
                // CountDownLatch
                // java多线程并发同步的一个工具类
                // 会传递进去一些数字，比如说1,2 ，3 都可以
                // 然后await()，如果数字不是0，那么久卡住，等待

                // 其他的线程可以调用coutnDown()，减1
                // 如果数字减到0，那么之前所有在await的线程，都会逃出阻塞的状态
                // 继续向下运行

                connectedSemaphore.await();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            log.info("ZooKeeper session established......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分布式锁
     * @param key
     */
    public void lock(String key) {
        try {
            zookeeper.create(key, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success to acquire lock for " + key);
        } catch (Exception e) {
            // 如果那个商品对应的锁的node，已经存在了，就是已经被别人加锁了，那么就这里就会报错
            // NodeExistsException
            int count = 0;
            while(true) {
                try {
                    Thread.sleep(20);
                    zookeeper.create(key, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    count++;
                    continue;
                }
                System.out.println("success to acquire lock for " + key +" after " + count + " times try......");
                break;
            }
        }
    }

    /**
     * 释放掉一个分布式锁
     * @param key
     */
    public void unlock(String key) {
        try {
            zookeeper.delete(key, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立zk session的watcher
     * @author Administrator
     *
     */
    private class ZooKeeperWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            System.out.println("Receive watched event: " + event.getState());
            if(KeeperState.SyncConnected == event.getState()) {
                connectedSemaphore.countDown();
            }
        }

    }

    /**
     * 封装单例的静态内部类
     * @author Administrator
     *
     */
    private static class Singleton {

        private static ZooKeeperSession instance;

        static {
            instance = new ZooKeeperSession();
        }

        public static ZooKeeperSession getInstance() {
            return instance;
        }

    }

    /**
     * 获取单例
     * @return
     */
    public static ZooKeeperSession getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化单例的便捷方法
     */
    public static void init() {
        getInstance();
    }

}

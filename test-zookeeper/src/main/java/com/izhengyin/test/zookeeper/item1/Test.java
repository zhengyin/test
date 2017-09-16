package com.izhengyin.test.zookeeper.item1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2017/9/4 下午4:34.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {

    private static Log logger = LogFactory.getLog(Test.class);

    private static MyZookeeper myZookeeper = new MyZookeeper();


    public static void main(String[] args) throws InterruptedException {
        new Thread(myZookeeper).start();

        int i = 0;
        while (true){
            i ++;

            if(i % 5 == 0){
                try {
                    Stat stat = myZookeeper.getZooKeeper().exists("/myZookeeper",null);
                    if(stat == null){
                        myZookeeper.getZooKeeper().create("/myZookeeper",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static class MyZookeeper implements Runnable{

        private ZooKeeper zooKeeper;


        @Override
        public void run() {
            try {
                zooKeeper = new ZooKeeper("zoo1:2181,zoo2:2182,zoo3:2183",3000,new Watcher(){
                    @Override
                    public void process(WatchedEvent event) {
                        logger.info(event);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public ZooKeeper getZooKeeper() {
            return zooKeeper;
        }
    }
}

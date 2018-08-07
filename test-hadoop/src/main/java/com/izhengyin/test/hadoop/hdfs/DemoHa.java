package com.izhengyin.test.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.IOException;

/**
 * Created by zhengyin on 2018/7/11 下午2:56.
 * Email  <zhengyin.name@gmail.com> .
 */
public class DemoHa {

    public static void main(String[] args){

        Path path = new Path("/tmp/test/ha");

        Configuration conf = new Configuration();

        //设置配置相关的信息，分别对应hdfs-site.xml core-site.xml
        conf.set("fs.defaultFS", "hdfs://kongfz");
        conf.set("dfs.nameservices", "kongfz");
        conf.set("dfs.ha.namenodes.kongfz", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.kongfz.nn1", "hadoop-master1:9000");
        conf.set("dfs.namenode.rpc-address.kongfz.nn2", "hadoop-master2:9000");
        conf.set("dfs.client.failover.proxy.provider.kongfz", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        //设置实现类，因为会出现类覆盖的问题
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        try {
            FileSystem fileSystem = FileSystem.get(conf);
            fileSystem.mkdirs(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

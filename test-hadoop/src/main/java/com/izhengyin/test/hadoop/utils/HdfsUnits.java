package com.izhengyin.test.hadoop.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;

import java.io.IOException;

/**
 * Created by zhengyin on 2018/6/8 上午11:24.
 * Email  <zhengyin.name@gmail.com> .
 */
public class HdfsUnits {
    public static Configuration getHdfsHaClusterConfig(){
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://izhengyin");
        configuration.set("dfs.nameservices", "izhengyin");
        configuration.set("dfs.ha.namenodes.hadoop2cluster" , "nn1,nn2");
        configuration.set("dfs.namenode.rpc-address.izhengyin.nn1" , "hadoop-master1:9000");
        configuration.set("dfs.namenode.rpc-address.izhengyin.nn2" , "hadoop-master2:9000");
        configuration.set("dfs.client.failover.proxy.provider.izhengyin" , "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        return configuration;
    }

}

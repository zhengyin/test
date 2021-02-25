package com.izhengyin.test.spark.sql;

import com.alibaba.fastjson.JSON;
import com.izhengyin.test.spark.RddTest;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.hive.HiveContext;

/**
 * Created by zhengyin on 2018/8/11 下午2:16.
 * Email  <zhengyin.name@gmail.com> .
 */
public class SparkSqlOnHiveTest {

    public static void main(String[] args) throws AnalysisException {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("lock");
        sparkConf.setAppName(SparkSqlOnHiveTest.class.getName());
        SparkContext sc = new SparkContext(sparkConf);
        Configuration conf = sc.hadoopConfiguration();
        //设置配置相关的信息，分别对应hdfs-site.xml core-site.xml
        conf.set("fs.defaultFS", "hdfs://izhengyin");
        conf.set("dfs.nameservices", "izhengyin");
        conf.set("dfs.ha.namenodes.izhengyin", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.izhengyin.nn1", "hadoop-master1:9000");
        conf.set("dfs.namenode.rpc-address.izhengyin.nn2", "hadoop-master2:9000");
        conf.set("dfs.client.failover.proxy.provider.izhengyin", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        //设置实现类，因为会出现类覆盖的问题
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        SparkSession sparkSession = SparkSession.builder()
                .master("lock")
                .appName(SparkSqlOnHiveTest.class.getName())
                .config("spark.sql.warehouse.dir","hdfs://izhengyin/spark/warehouse")
                .config("hive.metastore.uris","thrift://192.168.100.144:9083")
                .enableHiveSupport()
                .getOrCreate();

        sparkSession.sql("SELECT * FROM blog").show();

        Dataset<Row> df = sparkSession.read().parquet("hdfs://izhengyin/data/analysis/events/2018/8/10/dataFile_Events_analysis-job-02-vm_10.parquet");
        df.printSchema();
        df.createOrReplaceTempView("parquetFile");

        System.out.println(sparkSession.catalog().getTable("blog").description());

    }
}

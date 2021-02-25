package com.izhengyin.test.spark.sql;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.List;

/**
 * Created by zhengyin on 2018/8/11 下午2:50.
 * Email  <zhengyin.name@gmail.com> .
 */
public class DataFramesTest {


    public static void main(String[] args){


      //   readJson(getAndCreateSparkContextWithLocal());
        readParquet(getAndCreateSparkContextWithCluster());
    }



    private static void readParquet(SparkContext sparkContext){

        SparkSession sparkSession = SparkSession.builder().sparkContext(sparkContext).getOrCreate();

        String[] dataPaths = new String[]{
                "/data/analysis/events/2018/8/7/"
        };

        Dataset<Row> df = sparkSession.read().parquet(dataPaths);
        df.printSchema();
        df.createOrReplaceTempView("parquetFile");
        Dataset<Row> countDf = sparkSession.sql("SELECT  shop_id , event , count(distinct(client_ip)) as total from parquetFile group by  shop_id, event order by total desc");


        countDf.printSchema();
        countDf.show();



        /*
        JavaRDD<String> shopEventCountRDD = countDf.toJavaRDD().map(row -> {
            return row.getAs("shop_id")+","+row.getAs("event")+","+row.getAs("total");
        });
        */

        List<Tuple2<Integer,String>> shopEventCountList = countDf.toJavaRDD().mapToPair(row -> {
            return new Tuple2<Integer,String>(row.getAs("shop_id"),row.getAs("shop_id")+","+row.getAs("event")+","+row.getAs("total"));
        }).collect();

        for(Tuple2<Integer,String> eventCount : shopEventCountList){
            System.out.println(eventCount._1());
        }

    }
    private static void readJson(SparkContext sparkContext){
        SparkSession sparkSession = SparkSession.builder().sparkContext(sparkContext).getOrCreate();
        Dataset<Row> df = sparkSession.read().json(DataFramesTest.class.getClassLoader().getResource("data/events.dat").getFile());
        df.show();
        df.printSchema();
        df.groupBy("event").count().show();
        sparkSession.close();
    }

    private static SparkContext getAndCreateSparkContextWithLocal(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("lock");
        sparkConf.setAppName(DataFramesTest.class.getName());
        SparkContext sc = new SparkContext(sparkConf);
        return sc;
    }

    private static SparkContext getAndCreateSparkContextWithCluster(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("spark://log-system:7077");
        sparkConf.setAppName(DataFramesTest.class.getName());
        sparkConf.setJars(new String[]{
            "/Users/zhengyin/project/java/com.izhengyin.test/test-spark/target/test-spark-1.0.0.jar"
        });
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
        return sc;
    }
}

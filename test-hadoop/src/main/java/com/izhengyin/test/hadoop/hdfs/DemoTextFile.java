package com.izhengyin.test.hadoop.hdfs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.izhengyin.test.hadoop.AvroWriterSupport;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/7/11 下午2:56.
 * Email  <zhengyin.name@gmail.com> .
 */
public class DemoTextFile {

    private static Path filePath;
    public static void main(String[] args) throws InterruptedException{

        Configuration conf = new Configuration();

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
        FSDataOutputStream outputStream ;
        for(int i=0;i<100;i++){
            String filename = "hdfs://izhengyin/tmp/test/"+(i%10)+".json";
            System.out.println(filePath.toString());
            if(filePath != null && !filePath.toString().equals(filename)){

            }
            filePath = new Path(filename);

            try {
                FileSystem fileSystem = FileSystem.get(conf);
                if(fileSystem.exists(filePath.getParent())){
                    fileSystem.mkdirs(filePath.getParent());
                }
                System.out.println("Begin Write file into hdfs");
                TestRecord testRecord = new TestRecord(i,new Date().toString(),2,1,1L);

                if(fileSystem.exists(filePath)){
                    outputStream= fileSystem.append(filePath);
                }else{
                    outputStream = fileSystem.create(filePath);
                }
                outputStream.writeBytes(testRecord.toString()+"\t\n");
                System.out.println("End Write file into hdfs");
                if(i % 5 == 0){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            TimeUnit.SECONDS.sleep(1);
        }



    }
}

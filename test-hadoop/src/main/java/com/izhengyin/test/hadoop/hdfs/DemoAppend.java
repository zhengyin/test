package com.izhengyin.test.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

/**
 * Created by zhengyin on 2017/6/16.
 */
public class DemoAppend {
    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "hdfs://hadoop-master2:9000/data/testdata/db1/table/t4/year=2017/month=03/day=01/1.dat";
        Configuration configuration = new Configuration();
        configuration.setBoolean("dfs.support.append",true);
        FileSystem fileSystem = FileSystem.get(URI.create(filePath),configuration);
        int counter = 0;
        while (true){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<10000;i++){
                String s = (i+" "+i+":"+new Date().getTime()+"\n");
                sb.append(s);
                counter ++;
            }
            InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
            OutputStream out = fileSystem.append(new Path(filePath));
            IOUtils.copyBytes(in,out,1,true);
            in.close();
            out.close();
            System.out.println("counter:"+counter);
            Thread.sleep(1000);
        }

    }
}

package com.izhengyin.test.hadoop.parquet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Created by zhengyin on 2018/6/8 下午1:57.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ParquetRead {
    private static Logger logger = LoggerFactory.getLogger(ParquetRead.class);
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        logger.info("read file : "+filename);
        Path file = new Path(filename);
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://hadoop-master2:9000");
        GroupReadSupport groupReadSupport = new GroupReadSupport();

        printSchema(configuration,file);

        ParquetReader.Builder<Group> reader = ParquetReader.builder(groupReadSupport,file);
        reader.withConf(configuration);
        ParquetReader<Group> parquetReader = reader.build();
        Group line = null;
        while ((line = parquetReader.read()) != null){
            String city = line.getString("city",0);
            String ip = line.getString("ip",0);
            Group timeGroup = line.getGroup("time",0);
            Integer ttl = timeGroup.getInteger("ttl",0);
            String ttl2 = timeGroup.getString("ttl2",0);
            logger.info("Read line : city : "+ (city)+" ip : "+ (ip)+" ttl : "+ (ttl)+" ttl2 : "+ (ttl2));
        }
        logger.info("Read end.");

    }


    private static void printSchema(Configuration configuration , Path file) throws IOException {
        ParquetMetadata metadata = ParquetFileReader.readFooter(configuration,file, ParquetMetadataConverter.NO_FILTER);
        logger.info(file.getName()+" creator : "+metadata.getFileMetaData().getCreatedBy());
        logger.info(file.getName()+" schema : "+metadata.getFileMetaData().getSchema().toString());
    }
}

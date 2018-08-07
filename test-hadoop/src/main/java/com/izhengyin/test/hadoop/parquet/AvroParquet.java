package com.izhengyin.test.hadoop.parquet;

import com.izhengyin.test.hadoop.avro.ParquetAvro;
import com.izhengyin.test.hadoop.avro.Suit;
import com.izhengyin.test.hadoop.avro.md5;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by zhengyin on 2018/6/8 下午4:42.
 * Email  <zhengyin.name@gmail.com> .
 */
public class AvroParquet {
    private static Logger logger = LoggerFactory.getLogger(AvroParquet.class);
    public static void main(String[] args) throws IOException {
        String parquetFilename = args[0];
        String avroFilename = args[1];
        logger.info("write file : "+parquetFilename+" , "+avroFilename);
    //    writeAvroToDisk(avroFilename);
    //    readAvroFromDisk(avroFilename);
    //    readAvroAndWriteToParquet(parquetFilename,avroFilename);

        readParquetToAvro(parquetFilename);

    }

    private static void writeAvroToDisk(String avroFilename) throws IOException {

        ParquetAvro.Builder builder = ParquetAvro.newBuilder();

        builder.setBooleanVar(true);
        ByteBuffer buffer = ByteBuffer.allocate(3);
        buffer.put((byte) 1).put((byte)2).put((byte)3);
        builder.setBytesVar(buffer);
        builder.setIntVar(1234567890);
        builder.setLongVar(1234567890123L);
        builder.setFloatVar((float) 1234567.1234567);
        builder.setDoubleVar(123456789.123456789);
        builder.setStringVar("abcdef");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        builder.setIntArrayVar(integerList);

        List<CharSequence> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        builder.setStrArrayVar(stringList);

        Map<CharSequence,Long> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("a",1L);
        stringIntegerMap.put("b",2L);
        stringIntegerMap.put("c",3L);
        builder.setMapVar(stringIntegerMap);


        builder.setEnumVar(Suit.CLUBS);

        md5 md5 = new md5();
        builder.setFixedVar(md5);

        ParquetAvro parquetAvro = builder.build();

        File file = new File(avroFilename);
        DatumWriter<ParquetAvro> parquetAvroDatumWriter = new SpecificDatumWriter<ParquetAvro>(ParquetAvro.class);
        DataFileWriter<ParquetAvro> dataFileWriter = new DataFileWriter<ParquetAvro>(parquetAvroDatumWriter);
        if(file.exists()){
            System.out.println("AppendTo:"+avroFilename);
            dataFileWriter.appendTo(file);
        }else{
            dataFileWriter.create(new ParquetAvro().getSchema(),file);
            System.out.println("Create File:"+avroFilename);
        }

        dataFileWriter.append(parquetAvro);
        dataFileWriter.close();

    }

    private static void readAvroFromDisk(String avroFilename) throws IOException{
        File file = new File(avroFilename);
        DatumReader<ParquetAvro> parquetAvroDatumReader = new SpecificDatumReader<ParquetAvro>(ParquetAvro.class);
        DataFileReader<ParquetAvro> dataFileReader = new DataFileReader<ParquetAvro>(file, parquetAvroDatumReader);
        ParquetAvro parquetAvro = null;
        logger.info(" reader >>> >>> >>> >>> >>> >>> ");
        while (dataFileReader.hasNext()) {
            parquetAvro = dataFileReader.next(parquetAvro);
            logger.info(parquetAvro.toString());
        }
        logger.info(" <<< <<< <<< <<< <<< <<< <<< reader ");
    }

    private static void readAvroAndWriteToParquet(String parquetFilename , String avroFilename) throws IOException {

        Path path = new Path(parquetFilename);
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://hadoop-master2:9000");
        ParquetWriter<Object> parquetWriter = AvroParquetWriter.builder(path)
                .withConf(configuration)
                .withSchema(ParquetAvro.getClassSchema()).build();
        FileSystem fileSystem = FileSystem.get(configuration);
        if(fileSystem.exists(path)){
            logger.warn("parquetFilename exists.");
            return;
        }

        DatumReader<ParquetAvro> parquetAvroDatumReader = new SpecificDatumReader<ParquetAvro>(ParquetAvro.class);
        DataFileReader<ParquetAvro> dataFileReader = new DataFileReader<ParquetAvro>(new File(avroFilename), parquetAvroDatumReader);
        ParquetAvro parquetAvro = null;
        logger.info(" read and write >>> >>> >>> >>> >>> >>> ");
        while (dataFileReader.hasNext()) {
            parquetAvro = dataFileReader.next(parquetAvro);
            logger.info(parquetAvro.toString());
            parquetWriter.write(parquetAvro);
            logger.info("write line : "+parquetAvro.toString());
        }
        logger.info("write end!");
        parquetWriter.close();
    }

    /**
     *
     * @param parquetFilename
     */
    private static void readParquetToAvro(String parquetFilename) throws IOException {
        Path path = new Path(parquetFilename);
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://hadoop-master2:9000");
        ParquetReader<Object> parquetReader = AvroParquetReader.builder(path).withConf(configuration).build();
        Object line = null;
        logger.info(" read : "+parquetFilename);
        while ((line = parquetReader.read()) != null){
            line = (ParquetAvro) line;
            logger.info("Read line : "+ line.toString());
        }
        logger.info(" read end.");

        printSchema(configuration,path);
    }

    private static void printSchema(Configuration configuration , Path file) throws IOException {
        ParquetMetadata metadata = ParquetFileReader.readFooter(configuration,file, ParquetMetadataConverter.NO_FILTER);
        logger.info(file.getName()+" creator : "+metadata.getFileMetaData().getCreatedBy());
        logger.info(file.getName()+" schema : "+metadata.getFileMetaData().getSchema().toString());
    }
}

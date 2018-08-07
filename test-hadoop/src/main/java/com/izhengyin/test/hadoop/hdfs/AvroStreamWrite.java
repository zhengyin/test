package com.izhengyin.test.hadoop.hdfs;

import com.izhengyin.test.hadoop.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.ch.sctp.Shutdown;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengyin on 2018/7/12 下午12:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class AvroStreamWrite {

    public static void main(String[] args){
        Writer<User> writer = new Writer<User>();
        for (int i=0;i<10;i++){
            new Process(writer).start();
        }

    }




    private static class Process extends Thread{
        private Writer<User> writer;
        Process(Writer<User>  writer){
            this.writer = writer;
        }

        @Override
        public void run() {
            for (int i=0;i<100;i++){
                User user = new User("xiaomi",i,"green");
                try {
                    writer.write(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private static class Writer<T extends SpecificRecord>{

        private String dataPath = "/tmp/analysis";
        private final DatumWriter<T> datumWriter = new SpecificDatumWriter<T>();
        private final Map<String,DataFileWriter<T>> dataFileWriters = new ConcurrentHashMap<>();
        private final Logger logger = LoggerFactory.getLogger(Writer.class);
        private int counter = 0;

        synchronized void write(T data) throws IOException {
            createDataDir(getDataPath());
            File dataFile = new File(getDataFile(data.getClass().getSimpleName()));
            DataFileWriter<T> dataFileWriter = getDataFileWriter(dataFile,data.getSchema());
            dataFileWriter.append(data);
            counter ++;
            if(counter >= 10){
                dataFileWriter.flush();
            }
        }

        synchronized void close() throws IOException {
            closeAllDataFileWriter();
        }

        /**
         * 获取 dataFileWriter ， 当新产生 dataFileWriter 时先关闭其他的
         * @param dataFile
         * @return
         * @throws IOException
         */
        private DataFileWriter<T> getDataFileWriter(File dataFile , Schema schema) throws IOException {
            String key = dataFile.getAbsolutePath();
            DataFileWriter<T> dataFileWriter = dataFileWriters.get(key);
            if(dataFileWriter == null){
                closeAllDataFileWriter();
                dataFileWriter =  new DataFileWriter<T>(datumWriter);
                if(dataFile.exists()){
                    dataFileWriter.appendTo(dataFile);
                }else{
                    dataFileWriter.create(schema, dataFile);
                }
                dataFileWriters.put(key,dataFileWriter);
            }
            return dataFileWriter;
        }

        /**
         * close 所有
         * @throws IOException
         */
        private void closeAllDataFileWriter() throws IOException {
            for(String key : dataFileWriters.keySet()){
                DataFileWriter<T> dataFileWriter = dataFileWriters.get(key);
                if(dataFileWriter != null){
                    dataFileWriter.close();
                }
                dataFileWriters.remove(key);
            }
        }

        /**
         * 获取数据文件
         * @param eventName
         * @return
         */
        private String getDataFile(String eventName){
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            String filename = "dataFile_"+eventName+"_"+calendar.get(Calendar.HOUR_OF_DAY)+"/";
            StringBuilder sb = new StringBuilder();
            sb.append(getDataPath());
            sb.append("/");
            sb.append("dataFile_");
            sb.append(eventName);
            sb.append("_");
            sb.append(calendar.get(Calendar.HOUR_OF_DAY));
            sb.append(".avro");
            return sb.toString();
        }

        /**
         * 获取数据目录
         * @return
         */
        private String getDataPath(){
            return this.dataPath+"/"+getDataPartitionPath();
        }

        /**
         * 获取数据分区路径
         * @return partition
         */
        private String getDataPartitionPath(){
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            StringBuilder sb = new StringBuilder();
            sb.append(calendar.get(Calendar.YEAR));
            sb.append("/");
            sb.append(calendar.get(Calendar.MONTH) + 1);
            sb.append("/");
            sb.append(calendar.get(Calendar.DAY_OF_MONTH));
            return sb.toString();
        }

        /**
         * 创建数据目录
         * @param dataPath
         * @throws IOException
         */
        private void createDataDir(String dataPath) throws IOException{
            File file = new File(dataPath);
            if(!file.exists()){
                if(file.mkdirs()){
                    logger.info("Create data directory , "+dataPath);
                }
            }
            if(!file.isDirectory()){
                throw new IOException(dataPath+" create fail or it not is directory");
            }
        }
    }
}

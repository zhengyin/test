package com.izhengyin.test.hadoop.events;

import com.izhengyin.test.hadoop.avro.SimpleEvent;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2017/6/17.
 */
public class SimpileEventsSave {

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
       long id = 0;
       String beforeFile = "";
       String curFile = "";
       while (true){

           String filename = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
           curFile = "data/avro/simpileEvents/"+filename+".avro";

           File file = new File(curFile);
           DatumWriter<SimpleEvent> userDatumWriter = new SpecificDatumWriter<SimpleEvent>(SimpleEvent.class);
           DataFileWriter<SimpleEvent> dataFileWriter = new DataFileWriter<SimpleEvent>(userDatumWriter);
           if(file.exists()){
               dataFileWriter.appendTo(file);
           }else{
               dataFileWriter.create(new SimpleEvent().getSchema(),file);
               if(beforeFile != ""){
                   appendToHdfs(beforeFile);
               }
               System.out.println("Create File:"+curFile);
           }

           if(!curFile.equals(beforeFile)){
               beforeFile = curFile;
           }

           for(int i=0;i<1000;i++){
               id ++;
               String uuid = "uuid_"+getUuid();
               int eventId = getEventId();
               String _date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
               int _timestamp = (int)(System.currentTimeMillis()/1000);
               SimpleEvent event = new SimpleEvent(id,uuid,eventId,_date,_timestamp);
               dataFileWriter.append(event);
           }
           System.out.println("Write total : "+id);
           dataFileWriter.close();
           TimeUnit.SECONDS.sleep(1);
       }
    }

    private static int getUuid(){
        int max=10000;
        int min=1;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

    private static int getEventId(){
        int min=1;
        int max = 20;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }


    private static void appendToHdfs(String localfile) throws ParseException, IOException {
        File file = new File(localfile);
        String filename = file.getName();
        Date date = new SimpleDateFormat("YMdHm").parse(filename.replace(".avro",""));
        String partition_month =  filename.substring(0,6);
        String partition_day = filename.substring(6,8);
        String partition_hour = filename.substring(8,10);
        String hdfsPath = "/data/testdata/today_events/month="+partition_month+"/day="+partition_day+"/hour="+partition_hour+"/";
        String hdfsFile = hdfsPath+filename;

        Configuration conf = new Configuration();
        conf.set("fs.default.name","hdfs://hadoop:9000");
        FileSystem fs = FileSystem.get(conf);
        Path dirPath = new Path(hdfsPath);
        if(!fs.exists(dirPath)){
            fs.mkdirs(dirPath);
        }
        Path srcPath = new Path(localfile); //原路径
        Path dstPath = new Path(hdfsFile); //目标路径
        fs.copyFromLocalFile(true,srcPath, dstPath);
        System.out.println("Append to HDFS , local : "+ localfile +" hdfs : "+hdfsFile);
    }
}

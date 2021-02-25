package com.izhengyin.test.hadoop.events;

import com.izhengyin.test.hadoop.avro.Event;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * Created by zhengyin on 2017/6/20.
 */
public class EventsSave {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        String filename = "1.avro";
        File file = new File(filename);
        DatumWriter<Event> userDatumWriter = new SpecificDatumWriter<Event>(Event.class);
        DataFileWriter<Event> dataFileWriter = new DataFileWriter<Event>(userDatumWriter);
        if(file.exists()){
            System.out.println("AppendTo:"+filename);
            dataFileWriter.appendTo(file);
        }else{
            dataFileWriter.create(new Event().getSchema(),file);
            System.out.println("Create File:"+filename);
        }


        Map<CharSequence,CharSequence> eventAttr = new HashMap<CharSequence, CharSequence>();
        eventAttr.put("$country","a");
        eventAttr.put("$province","b");
        eventAttr.put("$city","c");
        eventAttr.put("$screen_height","100");
        eventAttr.put("$screen_width","200");
        eventAttr.put("$app_version","1.1.1");
        eventAttr.put("$browser","Chrome");
        eventAttr.put("$browser_version","Chrome31");
        eventAttr.put("$model","iphone6");
        eventAttr.put("$os","Ios");
        eventAttr.put("$os_version","7.0");
        Map<CharSequence,CharSequence> event_str_args = new HashMap<CharSequence, CharSequence>();
        event_str_args.put("name1","val1");
        event_str_args.put("name2","val2");
        Map<CharSequence,Double> event_int_args = new HashMap<CharSequence, Double>();
        event_int_args.put("name1",1.0);
        event_int_args.put("name2",2.0);
        Event event = Event.newBuilder()
                .setEvent("event")
                .setUserid(1)
                .setUuid("uuid")
                .setIp("182.1.2.1")
                .setSChannel("baidu")
                .setSelfUrl("http://www.izhengyin.com/path/to/b")
                .setRefUrl("http://www.izhengyin.com/path/to/a")
                .setAppName("孔夫子旧书网-书店")
                .setPlatform("PC")
                .setEventDate("2017-06-20")
                .setEventTime((int)(new Date().getTime()/1000))
                .setEventAttr(eventAttr)
                .setEventStringArgs(event_str_args)
                .setEventDoubleArgs(event_int_args)
                .setAttachFields1(1)
                .setAttachFields2("a").build();

        dataFileWriter.append(event);
        dataFileWriter.close();

        DatumReader<Event> userDatumReader = new SpecificDatumReader<Event>(Event.class);
        DataFileReader<Event> dataFileReader = new DataFileReader<Event>(file, userDatumReader);
        Event event1 = null;
        System.out.println(" reader >>> >>> >>> >>> >>> >>> ");
        while (dataFileReader.hasNext()) {
            event1 = dataFileReader.next(event1);
            System.out.println(event1);
        }
        System.out.println(" <<< <<< <<< <<< <<< <<< <<< reader ");
    }

}

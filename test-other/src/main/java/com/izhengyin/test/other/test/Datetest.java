package com.izhengyin.test.other.test;

import scala.Int;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhengyin on 2017/6/9.
 */
public class Datetest {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
    public static void main(String[] args) throws ParseException {


        System.out.println(getNextHour());
        System.exit(1);


        SimpleDateFormat timestamoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.ENGLISH);




        String[] timestamps = new String[]{
                "2018-07-11T01:21:54.000Z",
                "2018-07-11T01:07:30.000Z",
                "2018-07-11T01:07:11.000Z",
                "2018-07-11T01:37:00.000Z",
                "2018-07-10T01:21:54.000Z",
                "2018-07-01T08:21:54.000Z",
                "2017-07-01T01:21:54.000Z",
                "2018-07-10T05:56:03.000Z"
        };

        for(String time : timestamps){
            timestamoFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = timestamoFormat.parse(time);
            System.out.println(time +" -> "+dateFormat.format(date)+" -> "+ date.getTime());
        }


        System.exit(1);


        String time = "28/May/2017:23:59:02 +0800";

        Calendar cal = Calendar.getInstance();


        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
        Date date = sourceFormat.parse(time);
        System.out.println(date.getTime());


        String time2 = "2018-07-02T10:01:35.000Z";
        SimpleDateFormat sourceFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:sss", Locale.ENGLISH);
        Date date2 = sourceFormat2.parse(time2);
        System.out.println(date2.getTime());

    }


    public static Date getNextHour(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            long timestamp = sdf.parse(sdf.format(new Date())).getTime() + (calendar.get(Calendar.HOUR_OF_DAY) + 1) * 3600 * 1000;
            return new Date(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.izhengyin.test.spark;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.izhengyin.test.spark.pojo.EventsOriginalData;
import com.izhengyin.test.spark.pojo.ShopEventCount;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengyin on 2018/8/8 上午10:38.
 * Email  <zhengyin.name@gmail.com> .
 */
public class RddTest {


    public static void main(String[] args){
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName(RddTest.class.getName());
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> eventsWithStrRdds = jsc.textFile(RddTest.class.getClassLoader().getResource("data/events.dat").getFile());

        JavaRDD<EventsOriginalData> eventsRdds = eventsWithStrRdds.map((val) -> {
            return JSON.parseObject(val,new TypeReference<EventsOriginalData>(){});
        });

        eventsRdds.cache();

        countEventPv(eventsRdds);

        countEventUv(eventsRdds);

        countShopEventPv(eventsRdds);

    }


    private static void countEventPv(JavaRDD<EventsOriginalData> eventsRdds){
        JavaPairRDD<String,Integer> eventPvRdds = eventsRdds.mapToPair((val) -> {
            return new Tuple2<String,Integer>(val.getEvent(),1);
        });
        List<Tuple2<String,Integer>> result = eventPvRdds.reduceByKey((x, y)->{
            return  x + y;
        }).sortByKey().collect();


        print1(result);

    }


    private static void countEventUv(JavaRDD<EventsOriginalData> eventsRdds){
        List<Tuple2<String,Integer>> result = eventsRdds.map((val) -> {
            return val.getEvent()+","+val.getUuid();
        }).distinct().mapToPair((val) -> {
            String[] tmpArr = val.split(",");
            return new Tuple2<>(tmpArr[0],1);
        }).reduceByKey((x,y)->{
            return x+y;
        }).sortByKey().collect();
        print1(result);
    }

    private static void countShopEventPv(JavaRDD<EventsOriginalData> eventsRdds){
        JavaPairRDD<String,EventsOriginalData> eventPvRdds = eventsRdds.mapToPair((val) -> {
            return new Tuple2<String,EventsOriginalData>(val.getPlatform(),val);
        });

        JavaPairRDD<String,Map<String,Integer>> platforms = eventPvRdds.groupByKey().mapToPair((val) -> {
            Iterator<EventsOriginalData> iterator =  val._2().iterator();
            Map<String,Integer> tmpMap = new HashMap<>();
            while (iterator.hasNext()){
                EventsOriginalData data = iterator.next();
                Integer count = tmpMap.get(data.getEvent());
                if(count == null){
                    count = 0;
                }
                tmpMap.put(data.getEvent(),count + 1);
                System.out.println(JSON.toJSONString(data));
            }
            return new Tuple2<String,Map<String,Integer>>(val._1(),tmpMap);
        });


        List<Tuple2<String,Map<String,Integer>>> result =  platforms.sortByKey().collect();

        print3(result);
    }

    private static void print1(final List<Tuple2<String,Integer>> result){
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ");
        int count = 0;
        for(Tuple2<String,Integer> v : result){
            System.out.println(v._1()+" -> "+v._2());
            count += v._2();
        }
        System.out.println("count -> "+count);
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ");
    }

    private static void print2(final List<Tuple2<String,ShopEventCount>> result){
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ");
        for(Tuple2<String,ShopEventCount> v : result){
            System.out.println(v._1()+" -> "+JSON.toJSONString(v._2()));
        }
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ");
    }

    private static void print3(final List<Tuple2<String,Map<String,Integer>>> result){
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ");
        for(Tuple2<String,Map<String,Integer>> v : result){
            System.out.println(v._1()+" -> "+JSON.toJSONString(v._2()));
        }
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ");
    }
}

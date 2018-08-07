package com.izhengyin.test.other.serial;

import com.alibaba.fastjson.JSON;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengyin on 2018/7/30 下午2:25.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ProtostuffTest {
    private static RuntimeSchema<EventsStream> schema = RuntimeSchema.createFrom(EventsStream.class);
    public static void main(String[] args){
        EventsStream events = new EventsStream();
        events.setEvent("abcdef");
        events.setApp_name("孔夫子旧书网");
        Map<CharSequence,Integer> countAttr = new HashMap<>();
        countAttr.put("abc",1);
        countAttr.put("def",2);
        events.setCount_attr(countAttr);


        System.out.println(JSON.toJSONString(events));
        /**
         * 序列化
         */
        byte[] bytes = ProtostuffIOUtil.toByteArray(events,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));


        /**
         * 反序列化
         */
        EventsStream newEvents = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes,newEvents,schema);
        System.out.println(JSON.toJSONString(newEvents));
    }
}

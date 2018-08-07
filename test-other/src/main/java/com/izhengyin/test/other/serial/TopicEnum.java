package com.izhengyin.test.other.serial;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhengyin on 2018/7/30 下午2:59.
 * Email  <zhengyin.name@gmail.com> .
 */

public enum TopicEnum {
    EVENTS_AVOR(AvroProducer.TOPIC,new Events()); //实例

    private String topic;
    private SpecificRecordBase record;

    private TopicEnum(String topic,SpecificRecordBase record){
        this.topic = topic;
        this.record = record;
    }

    public static TopicEnum getTopicEnum(String topicName){
        if (topicName.isEmpty()){
            return null;
        }

        for (TopicEnum topicEnum : values()){
            if (StringUtils.equalsIgnoreCase(topicEnum.getTopic(),topicName)){
                return topicEnum;
            }
        }
        return null;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public SpecificRecordBase getRecord() {
        return record;
    }

    public void setRecord(SpecificRecordBase record) {
        this.record = record;
    }
}

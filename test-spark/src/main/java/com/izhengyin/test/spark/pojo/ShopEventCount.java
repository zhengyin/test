package com.izhengyin.test.spark.pojo;

import java.io.Serializable;

/**
 * Created by zhengyin on 2018/8/8 下午4:10.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ShopEventCount implements Serializable{
    private Integer shopId;
    private String event;
    private Integer count;

    public ShopEventCount(){

    }

    public ShopEventCount(Integer shopId , String event, Integer count) {
        this.shopId = shopId;
        this.event = event;
        this.count = count;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ShopEventCount{" +
                "shopId=" + shopId +
                ", event='" + event + '\'' +
                ", count=" + count +
                '}';
    }
}

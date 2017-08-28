package com.izhengyin.test.mybatis.springboot.mulitds.dimain;

import java.sql.Timestamp;

/**
 * Created by zhengyin on 2017/8/26 上午10:55.
 * Email  <zhengyin.name@gmail.com> .
 */
public class News {
    private int id;
    private String title;
    private String content;
    private Timestamp addTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}

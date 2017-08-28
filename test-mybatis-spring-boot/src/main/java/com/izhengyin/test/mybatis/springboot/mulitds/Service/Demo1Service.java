package com.izhengyin.test.mybatis.springboot.mulitds.Service;

import com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo1.NewsMapperFromDemo1;
import com.izhengyin.test.mybatis.springboot.mulitds.dimain.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhengyin on 2017/8/26 下午3:24.
 * Email  <zhengyin.name@gmail.com> .
 */
@Service
public class Demo1Service {

    private NewsMapperFromDemo1 newsMapperFromDemo1;

    public Demo1Service(NewsMapperFromDemo1 newsMapperFromDemo1) {
        this.newsMapperFromDemo1 = newsMapperFromDemo1;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public News add(String title, String content){
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setAddTime(new Timestamp(new Date().getTime()));
        newsMapperFromDemo1.insert(news);
        return news;
    }
    @Transactional(rollbackFor = RuntimeException.class)
    public int modify(int id,String title, String content){
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setContent(content);
        news.setAddTime(new Timestamp(new Date().getTime()));
        return newsMapperFromDemo1.update(news);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public int del(int id,boolean throwEX) throws RuntimeException{
        int row = newsMapperFromDemo1.delete(id);
        if(throwEX){
            throw new RuntimeException();
        }
        return row;
    }
}

package com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo1;

import com.izhengyin.test.mybatis.springboot.mulitds.dimain.News;
import org.apache.ibatis.annotations.*;

/**
 * Created by zhengyin on 2017/8/26 上午10:50.
 * Email  <zhengyin.name@gmail.com> .
 */
@Mapper
public interface NewsMapperFromDemo1 {
    @Insert("INSERT INTO news(title,content,addTime) VALUES(#{title},#{content},#{addTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(News news);

    @Delete("DELETE FROM news WHERE id=#{id}")
    public int delete(@Param("id") Integer id);

    @Update("UPDATE news set title=#{title},content=#{content},addTime=#{addTime} WHERE  id=#{id}")
    public int update(News news);

    @Select("SELECT * FROM news WHERE id = #{id}")
    public News select(@Param("id") Integer id);
}

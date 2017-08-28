package com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo2;

import com.izhengyin.test.mybatis.springboot.mulitds.dimain.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhengyin on 2017/8/26 上午10:50.
 * Email  <zhengyin.name@gmail.com> .
 */
@Mapper
public interface NewsMapperFromDemo2 {
    @Select("SELECT  * FROM news WHERE id = #{id}")
    public News select(@Param("id") Integer id);
}

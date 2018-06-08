package com.izhengyin.test.mybatis.springboot.mulitds.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by zhengyin on 2017/8/26 上午10:15.
 * Email  <zhengyin.name@gmail.com> .
 *
 * db [数据库名名称]
 *
 */

@Configuration
public class DataSourcePropertiesConfiguration {

    @Primary
    @Qualifier("demo1DataSourceProperties")
    @Bean(name = "demo1DataSourceProperties")
    @ConfigurationProperties("spring.datasource.demo1")
    public DataSourceProperties demo1DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Qualifier("demo2DataSourceProperties")
    @Bean(name = "demo2DataSourceProperties")
    @ConfigurationProperties("spring.datasource.demo2")
    public DataSourceProperties demo2DataSourceProperties(){
        return new DataSourceProperties();
    }

}

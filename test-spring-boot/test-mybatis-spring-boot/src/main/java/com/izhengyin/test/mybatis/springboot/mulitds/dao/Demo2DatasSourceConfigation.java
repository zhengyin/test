package com.izhengyin.test.mybatis.springboot.mulitds.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by zhengyin on 2017/8/26 上午10:23.
 * Email  <zhengyin.name@gmail.com> .
 */
@Configuration
@MapperScan(basePackages = "com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo2", sqlSessionFactoryRef = "demo2SqlSessionFactory")
public class Demo2DatasSourceConfigation {
    @Qualifier("demo2DataSourceProperties")
    private DataSourceProperties demo2DataSourceProperties;
    private MybatisProperties mybatisProperties;

    public Demo2DatasSourceConfigation(@Qualifier("demo2DataSourceProperties") DataSourceProperties demo2DataSourceProperties , MybatisProperties mybatisProperties){
        this.demo2DataSourceProperties = demo2DataSourceProperties;
        this.mybatisProperties = mybatisProperties;
    }

    @Qualifier("dataSourceDemo2")
    @Bean(name = "dataSourceDemo2", destroyMethod =  "close")
    public DataSource dataSourceDemo2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(demo2DataSourceProperties.getUrl());
        dataSource.setUsername(demo2DataSourceProperties.getUsername());//用户名
        dataSource.setPassword(demo2DataSourceProperties.getPassword());//密码
        dataSource.setDriverClassName(demo2DataSourceProperties.getDriverClassName());
        dataSource.setInitialSize(2);	//初始化时建立物理连接的个数
        dataSource.setMaxActive(20);	//最大连接池数量
        dataSource.setMinIdle(0);		//最小连接池数量
        dataSource.setMaxWait(60000);	//获取连接时最大等待时间，单位毫秒。
        dataSource.setValidationQuery("SELECT 1");	//用来检测连接是否有效的sql
        dataSource.setTestOnBorrow(false);	//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }

    @Qualifier("demo2SqlSessionFactory")
    @Bean(name = "demo2SqlSessionFactory")
    public SqlSessionFactoryBean demo2SqlSessionFactory(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setVfs(SpringBootVFS.class);
        factoryBean.setDataSource(dataSourceDemo2());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        if (mybatisProperties.getConfiguration() != null) {
            BeanUtils.copyProperties(mybatisProperties.getConfiguration(), configuration);
        }
        return factoryBean;
    }

}

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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by zhengyin on 2017/8/26 上午10:23.
 * Email  <zhengyin.name@gmail.com> .
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo1", sqlSessionFactoryRef = "demo1SqlSessionFactory")
public class Demo1DatasSourceConfigation {

    private DataSourceProperties demo1DataSourceProperties;
    private MybatisProperties mybatisProperties;
    public Demo1DatasSourceConfigation(DataSourceProperties demo1DataSourceProperties , MybatisProperties mybatisProperties){
        this.demo1DataSourceProperties = demo1DataSourceProperties;
        this.mybatisProperties = mybatisProperties;
    }



    @Primary
    @Qualifier("dataSourceDemo1")
    @Bean(name = "dataSourceDemo1" , destroyMethod =  "close")
    public DataSource dataSourceDemo1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(demo1DataSourceProperties.getUrl());
        dataSource.setUsername(demo1DataSourceProperties.getUsername());//用户名
        dataSource.setPassword(demo1DataSourceProperties.getPassword());//密码
        dataSource.setDriverClassName(demo1DataSourceProperties.getDriverClassName());
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
    @Primary
    @Qualifier("demo1SqlSessionFactory")
    @Bean(name = "demo1SqlSessionFactory")
    public SqlSessionFactoryBean demo1SqlSessionFactory(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setVfs(SpringBootVFS.class);
        factoryBean.setDataSource(dataSourceDemo1());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        if (mybatisProperties.getConfiguration() != null) {
            BeanUtils.copyProperties(mybatisProperties.getConfiguration(), configuration);
        }
        return factoryBean;
    }


    //事务配置
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSourceDemo1());
    }

}

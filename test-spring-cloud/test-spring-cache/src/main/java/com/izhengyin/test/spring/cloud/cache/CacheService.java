package com.izhengyin.test.spring.cloud.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhengyin on 2018/1/18 下午1:47.
 * Email  <zhengyin.name@gmail.com> .
 */
@Service
public class CacheService {



    private Log logger = LogFactory.getLog(CacheService.class);

    @Cacheable(cacheNames = "cache1"  , cacheManager = "redisCache")
    public String cache1(){
        logger.info(" >>>>>>>>>>> get cache1 ");
        return " | cache1 ";
    }

    @Cacheable(cacheNames = "cache1" , key = "#val" , cacheManager = "redisCache")
    public String cache1(String val){
        logger.info(" >>>>>>>>>>> get cache1 ["+val+"]");
        return " | cache1 ["+val+"]";
    }

    @Cacheable(cacheNames = "cache2" , key = "#val", cacheManager = "redisCache")
    public String cache2(String val){
        logger.info(" >>>>>>>>>>> get cache2 ["+val+"]");
        return "| cache2 ["+val+"]";
    }

    @Cacheable(cacheNames = "cache3" , key = "#val", cacheManager = "redisCache")
    public String cache3(String val){
        logger.info(" >>>>>>>>>>> get cache3 ["+val+"]");
        return "| cache3 ["+val+"]";
    }

    @CachePut(cacheNames = "cache3" , key = "#val" )
    public String CachePut3(String val){
        logger.info(" >>>>>>>>>>> get cache3 ["+val+"]");
        return "| cache3 ["+val+"]";
    }

    @CacheEvict(cacheNames = "cache3" , key = "#val" , allEntries = false , cacheManager = "redisCache")
    public void CacheEvict3(String val){
        logger.info(" >>>>>>>>>>> clean cache3 ");
    }

    @Cacheable(cacheNames = "#userId", cacheManager = "redisCache")
    public String dynamicCache(int userId){
        return "1";
    }

    @CacheEvict(cacheNames = "cache3" , allEntries = true, cacheManager = "redisCache")
    public void flushall(){
        logger.info(" >>>>>>>>>>> flushall cache3 ");
    }


    @Cacheable(cacheNames = "tableInfo"  , cacheManager = "caffeineCache")
    public String getTableInfo(){
        logger.info(" >>>>>>>>>>> get tableInfo ");
        return " | tableInfo "+ new Date().toString();
    }



}
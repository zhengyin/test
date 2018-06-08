package com.izhengyin.test.spring.cloud.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/1/18 下午1:46.
 * Email  <zhengyin.name@gmail.com> .
 */
@Configuration
public class CacheManagerConfig {


    @Primary
    @Bean("caffeineCache")
    public CacheManager caffeineCacheManager(){
        CaffeineCache tableInfoCache = new CaffeineCache("tableInfo",
                Caffeine.newBuilder().
                        expireAfterWrite(10, TimeUnit.SECONDS)
                        .maximumSize(30000)
                        .build());
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(tableInfoCache));

        return manager;
    }


    @Bean("redisCache")
    public CacheManager redisCacheManager(RedisTemplate redisTemplate){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(86400);
        Map<String,Long> expires = new HashMap<>();
        expires.put("cache1",10L);
        expires.put("cache2",20L);
        redisCacheManager.setExpires(expires);
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(){
        return new StringRedisTemplate(getConnectionFactory());
    }

    @Bean
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        return factory;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.redis")
    public JedisPoolConfig getRedisConfig(){
        return new JedisPoolConfig();
    }

}

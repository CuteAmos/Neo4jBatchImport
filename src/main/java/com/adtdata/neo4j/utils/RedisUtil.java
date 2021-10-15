package com.adtdata.neo4j.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author aixiaobai
 * @date 2021/10/14 11:11
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void set(String key,String value){
        if(value == null){
            value = "";
        }
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key){
        return stringRedisTemplate.delete(key);
    }

    public Set<String> getKeys(String patten){
        return  stringRedisTemplate.keys(patten);
    }

    public long deleteKeys(String patten){
        Set<String> keys = stringRedisTemplate.keys(patten);
        return stringRedisTemplate.delete(keys);
    }

}

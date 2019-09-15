package com.example.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName: CacheRedisService
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/9/15 16:18
 * @Version: 1.0
 */
@Service
public class CacheRedisServiceImpl implements CacheRedisService{

    @Override
    @Cacheable(value = "aboutUser", key = "'addUser_' + #id")
    public String addUser(String id){
        return id;
    }

}

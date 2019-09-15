package com.example.redis.Controller;

import com.example.redis.service.CacheRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CacheRedisController
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/9/15 16:29
 * @Version: 1.0
 */
@RestController
public class CacheRedisController {

    @Autowired
    private CacheRedisService cacheRedisService;

    @GetMapping("/addUser")
    public String addUser(){
        String id = "4";
        cacheRedisService.addUser(id);
        return id;
    }
}

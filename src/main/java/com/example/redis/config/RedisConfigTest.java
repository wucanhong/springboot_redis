package com.example.redis.config;

import com.example.redis.entity.User;
import com.example.redis.service.RedisService;
import com.example.redis.util.RedisKeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisConfigTest
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/9/14 19:28
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;



    @Test
    public void testObj() throws Exception{
        User user = new User();
        user.setAddress("上海");
        user.setName("测试名称");
        user.setAge(123);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        redisService.expireKey("name", 20, TimeUnit.SECONDS);
        String key = RedisKeyUtil.getKey(User.Table, "name", user.getName());
        User vo = (User)operations.get(key);
        System.out.println(vo);
    }

    @Test
    public void testValueOption() {
        User user = new User();
        user.setAddress("上海13333444443");
        user.setName("jantent");
        user.setAge(23);
        valueOperations.set("test1", user);

        System.out.println(valueOperations.get("test1"));
    }

    @Test
    public void testSetOperation() throws Exception {
        User user = new User();
        user.setAddress("北京");
        user.setName("jantent");
        user.setAge(23);
        User auser = new User();
        auser.setAddress("n柜昂周");
        auser.setName("antent");
        auser.setAge(23);
        setOperations.add("user:test", user, auser);
        Set<Object> result = setOperations.members("user:test");
        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception {
        User user = new User();
        user.setAddress("北京");
        user.setName("jantent");
        user.setAge(23);
        hashOperations.put("hash:user", user.hashCode() + "", user);
        System.out.println(hashOperations.get("hash:user", user.hashCode() + ""));
    }

    @Test
    public void  ListOperations() throws Exception {
        User user = new User();
        user.setAddress("北京");
        user.setName("jantent");
        user.setAge(23);
        listOperations.leftPush("list:user", user);
        //弹出之后，值会消失
        System.out.println(listOperations.leftPop("list:user"));

    }
}

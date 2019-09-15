package com.example.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName: config
 * @Author: 吴灿洪
 * @Description Redis相关Bean配置
 * @Date: 2019/9/14 15:19
 * @Version: 1.0
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{


    /**
     * 选择Redis作为默认的缓存器
     * 这里的create是springboot2.0的方法
     * 2.0之前的new RedisCacheManager 来创建redis工厂已经废弃不可用
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.create(factory);
        return redisCacheManager;
    }

    /**
     * redistemplate相关配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化redis的value值(默认使用JDK的序列化方式)
        Jackson2JsonRedisSerializer jacksonSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();

        //ALL指定要序列化的域，点进去看源码可以发现有get set等
        //ANY是包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String，Integer等会出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerializer.setObjectMapper(om);

        //值采用Json序列化
        template.setValueSerializer(jacksonSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key
        template.setKeySerializer(new StringRedisSerializer());

        //设置hash key和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 对hash类型的数据操作
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate){
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate){
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate){
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }
}

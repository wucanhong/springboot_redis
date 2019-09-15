package com.example.redis.util;

/**
 * @ClassName: RedisKeyUtil
 * @Author: 吴灿洪
 * @Description: redisKey设计
 * @Date: 2019/9/14 19:15
 * @Version: 1.0
 */
public class RedisKeyUtil {

    /**
     * reids 的key
     * 形式为：
     * 表名：主键名：主键值：列名
     * @param tableName
     * @param majorKey
     * @param majorKeyValue
     * @param column
     * @return
     */
    public static String getKeyWithColumn(String tableName, String majorKey, String majorKeyValue, String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }

    /**
     * redis的key
     * 形式为：
     * 表名：主键名：主键值
     *
     * @param tableName
     * @param majorKey
     * @param majorKeyValue
     * @return
     */
    public static String getKey(String tableName, String majorKey, String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue);
        return buffer.toString();
    }
}

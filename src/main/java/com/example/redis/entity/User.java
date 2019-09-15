package com.example.redis.entity;

import lombok.Data;

/**
 * @ClassName: UserVo
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/9/14 19:27
 * @Version: 1.0
 */
@Data
public class User {

    public  static final String Table = "user";

    private String name;
    private String address;
    private Integer age;
}

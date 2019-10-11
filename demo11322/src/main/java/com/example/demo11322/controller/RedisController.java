package com.example.demo11322.controller;

import com.example.demo11322.entity.UserEntity;
import com.example.demo11322.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019-10-09 09:13
 */
@RequestMapping("/redis")
@RestController
public class RedisController {
    /**
     * redis中存储的过期时间60s
     */
    private static int ExpireTime=60;
    @Resource
    private RedisUtil redisUtil;
    @RequestMapping("set")
    public boolean redisset(String key, String value){
        UserEntity userEntity =new UserEntity();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());
        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}

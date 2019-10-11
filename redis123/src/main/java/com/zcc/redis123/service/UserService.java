package com.zcc.redis123.service;

import com.zcc.redis123.dao.UserDao;
import com.zcc.redis123.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @date 2019-09-29 17:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 根据id获取
     */
    @Cacheable(cacheNames = "users",key="#id")
    public User findUserById(int id){
        System.out.println("开始查询数据库...");
        return userDao.findUserById(id);
    }
    @CacheEvict(value = "users",key = "#id")
    public void evictUser(int id) {
        System.out.println("evict user:" + id);
    }
}

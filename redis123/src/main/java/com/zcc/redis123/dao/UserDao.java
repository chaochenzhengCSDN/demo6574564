package com.zcc.redis123.dao;

import com.zcc.redis123.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2019-09-29 17:08
 */
public interface UserDao {
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User findUserById(int id);
}

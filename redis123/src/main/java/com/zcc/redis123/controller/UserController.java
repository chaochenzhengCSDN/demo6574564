package com.zcc.redis123.controller;

import com.zcc.redis123.entity.User;
import com.zcc.redis123.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2019-09-29 17:04
 */
@RestController
@RequestMapping("/web")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 获取对应记录
     */
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    User findUserById(int id){
        return userService.findUserById(id);
    }
    @GetMapping("/delUser/{id}")
    public String delUser(@PathVariable("id") int id){
        userService.evictUser(id);
        return "删除成功";
    }

}

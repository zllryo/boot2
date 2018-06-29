package com.ryo.boot2.controller;

import com.ryo.boot2.model.User;
import com.ryo.boot2.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

public class UserController {
    @Resource
    private UserService userService;


    public User getUserById(@PathVariable(value = "id")int id)
    {
        return userService.selectByPrimaryKey(id);
    }
}

package com.ryo.boot2.service;

import com.ryo.boot2.model.Girls;
import com.ryo.boot2.model.User;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    int insertAll(Girls model,User record);
}

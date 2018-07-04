package com.ryo.boot2.service.Impl;

import com.ryo.boot2.controller.aop.OperateLog;
import com.ryo.boot2.dao.GirlsMapper;
import com.ryo.boot2.dao.UserMapper;
import com.ryo.boot2.model.Girls;
import com.ryo.boot2.model.User;
import com.ryo.boot2.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private GirlsMapper girlsMapper;

    public   int deleteByPrimaryKey(Integer id)
    {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record)
    {
        return  userMapper.insert(record);
    }


    public User selectByPrimaryKey(Integer id)
    {
        return userMapper.selectByPrimaryKey(id);
    }


    @OperateLog
    public  List<User> selectAll()
    {
        return userMapper.selectAll();
    }


    public int updateByPrimaryKey(User record)
    {
        return  userMapper.updateByPrimaryKey(record);
    }

    /*
    * Transactional只回滚RuntimeException异常;或者设置@Transactional(rollbackFor = { Exception.class })
    * 对所有异常回滚
     * */
    @Transactional
    public int insertAll(Girls model,User record)
    {
        try
        {
            girlsMapper.insert(model);
            userMapper.insert(record);
            return 1;
        }
        catch (RuntimeException ex)
        {
            throw ex;
        }

    }
}

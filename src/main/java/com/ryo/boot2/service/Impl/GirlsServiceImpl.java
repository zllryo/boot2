package com.ryo.boot2.service.Impl;

import com.ryo.boot2.controller.aop.OperateLog;
import com.ryo.boot2.dao.GirlsMapper;
import com.ryo.boot2.model.Girls;
import com.ryo.boot2.service.GirlsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GirlsServiceImpl implements GirlsService {

    @Resource
    private GirlsMapper girlsMapper;

    public int deleteByPrimaryKey(Integer id) {
        return girlsMapper.deleteByPrimaryKey(id);
    }


    public int insert(Girls record) {
        return girlsMapper.insert(record);
    }


    public Girls selectByPrimaryKey(Integer id) {
        return girlsMapper.selectByPrimaryKey(id);
    }

    @OperateLog
    public List<Girls> selectAll() {
        return girlsMapper.selectAll();
    }


    public int updateByPrimaryKey(Girls record) {
        return girlsMapper.updateByPrimaryKey(record);
    }

}


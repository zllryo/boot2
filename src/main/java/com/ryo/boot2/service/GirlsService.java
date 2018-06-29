package com.ryo.boot2.service;

import com.ryo.boot2.model.Girls;

import java.util.List;

public interface GirlsService {

    int deleteByPrimaryKey(Integer id);


    int insert(Girls record);


    Girls selectByPrimaryKey(Integer id);


    List<Girls> selectAll();


    int updateByPrimaryKey(Girls record);
}

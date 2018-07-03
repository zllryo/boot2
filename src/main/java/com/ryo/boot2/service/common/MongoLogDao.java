package com.ryo.boot2.service.common;

import com.ryo.boot2.model.common.MongoLog;

public interface MongoLogDao {

    /**
     * 创建对象
     *
     * @param mongoLog
     */
    void insert(MongoLog mongoLog);

    /**
    * 更新对象
    *
    * @param mongoLog
    */
    void update(MongoLog mongoLog);

    /**
     * 根据ID查询对象
     *
     * @param id
     * @return
     */
     MongoLog findById(Object id);

     /**
     * 删除对象
     *
     * @param id
     */
     void delete(Object id);

}

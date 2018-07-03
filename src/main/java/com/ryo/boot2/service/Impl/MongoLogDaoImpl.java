package com.ryo.boot2.service.Impl;

import com.ryo.boot2.service.common.MongoLogDao;
import com.ryo.boot2.model.common.MongoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Criteria;
@Component
public class MongoLogDaoImpl implements MongoLogDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public  void insert(MongoLog mongoLog)
    {
        mongoTemplate.insert(mongoLog);
    }

    public  void update(MongoLog mongoLog)
    {
        Query query = new Query(Criteria.where("_id").is(mongoLog.getId()));
        Update update = new Update()
                .set("level", mongoLog.getLevel())
                .set("msg",mongoLog.getMsg())
                .set("threadname",mongoLog.getThreadname());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MongoLog.class);
    }

    public MongoLog findById(Object id)
    {
        Query query=new Query(Criteria.where("_id").is(id));
        return   mongoTemplate.findOne(query,MongoLog.class);
    }

    public  void delete(Object id)
    {
        Query query=new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,MongoLog.class);
    }

}

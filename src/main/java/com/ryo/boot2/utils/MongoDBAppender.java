package com.ryo.boot2.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.ryo.boot2.model.common.MongoLog;
import com.ryo.boot2.service.common.MongoLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {


    @Override
    protected void append(ILoggingEvent eventObject) {
        MongoLogDao mongoLogDao=ApplicationContextProvider.getBean(MongoLogDao.class);
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(8);
        Date now = df.parse(df.format(day), pos);
        MongoLog log = new MongoLog();
        log.setCreatetime(now);
        log.setLevel(eventObject.getLevel().toString());
        log.setMsg(eventObject.getMessage());
        log.setThreadname(eventObject.getThreadName());
        mongoLogDao.insert(log);

    }

}

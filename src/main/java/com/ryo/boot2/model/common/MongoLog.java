package com.ryo.boot2.model.common;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class MongoLog {

    @Id
    private  String id;

    private  Date createtime;

    private  String level;

    private  String msg;

    private  String threadname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getThreadname() {
        return threadname;
    }

    public void setThreadname(String threadname) {
        this.threadname = threadname;
    }
}

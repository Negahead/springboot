package com.google.springboot.entity.POJO;

import java.io.Serializable;

public class RedisLog implements Serializable {
    public static final long serialVersionUID = 1L;

    public RedisLog() {
    }

    public RedisLog(String time, String application, String subApplication, String level, String msg) {
        this.time = time;
        this.application = application;
        this.subApplication = subApplication;
        this.level = level;
        this.msg = msg;
    }

    private String time;
    private String application;
    private String subApplication;
    private String level;
    private String msg;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSubApplication() {
        return subApplication;
    }

    public void setSubApplication(String subApplication) {
        this.subApplication = subApplication;
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
}

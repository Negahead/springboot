package com.google.springboot.entity.response;

import java.io.Serializable;

public class MysqlDateTime implements Serializable {
    public static final long serialVersionUID = 1L;

    private int id;
    private int age;
    private String createdOn;
    private String type = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

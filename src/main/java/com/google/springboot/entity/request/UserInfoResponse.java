package com.google.springboot.entity.request;

import java.io.Serializable;

public class UserInfoResponse implements Serializable{
    public static final long serialVersionUID = 1L;

    private long userId;
    private String userName;
    private String userPWd;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPWd() {
        return userPWd;
    }

    public void setUserPWd(String userPWd) {
        this.userPWd = userPWd;
    }
}

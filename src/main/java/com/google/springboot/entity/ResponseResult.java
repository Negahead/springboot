package com.google.springboot.entity;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private String res = "";
    private String errCode = "";
    private T data;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult() {

    }

    public ResponseResult(T data) {
        this.data = data;
    }
}

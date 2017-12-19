package com.google.springboot.entity;

import com.google.springboot.entity.enums.ErrorCodeEnum;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private String res = "fail";
    private int errCode;
    private T data;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public ResponseResult(T data) {
        this.data = data;
        this.res = "success";
    }

    public ResponseResult(ErrorCodeEnum errorCodeEnum) {
        this.errCode = errorCodeEnum.getErrorCode();
    }


}

package com.google.springboot.entity.enums;

public enum ErrorCodeEnum {
    PARAMETER_ERROR(1,"parameter error");

    private int errorCode;
    private String errorDesc;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    ErrorCodeEnum(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }


}

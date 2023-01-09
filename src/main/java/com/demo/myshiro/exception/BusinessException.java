package com.demo.myshiro.exception;

import com.demo.myshiro.exception.code.BaseResponseCode;

public class BusinessException  extends Exception{

    private final String code;

    private final String msg;

    public BusinessException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(BaseResponseCode baseResponseCode){
        this.code = baseResponseCode.getCode();
        this.msg = baseResponseCode.getMsg();
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}

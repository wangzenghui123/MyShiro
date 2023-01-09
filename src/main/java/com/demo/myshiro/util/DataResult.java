package com.demo.myshiro.util;

import com.demo.myshiro.exception.code.BaseResponseCode;
import lombok.Data;

@Data
public class DataResult <T>{

    private String code;

    private String msg;

    private T data;

    public DataResult(){
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(String code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(String code, T data) {
        this.code = code;
        this.data = data;
        this.msg = null;
    }

    public DataResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    public DataResult(BaseResponseCode baseResponseCode){
        this.code = baseResponseCode.getCode();
        this.msg = baseResponseCode.getMsg();
    }

    public DataResult(BaseResponseCode baseResponseCode,T data){
        this.code = baseResponseCode.getCode();
        this.msg = baseResponseCode.getCode();
        this.data = data;
    }

    public static DataResult getResult(String code,String msg){
        return new DataResult(code,msg);
    }

    public  static <T>DataResult getResult(String code,T data){
        return new DataResult(code,data);
    }

    public static <T> DataResult getResult(String code,String msg,T data){
        return new DataResult(code,msg,data);
    }

    public static DataResult getResult(BaseResponseCode baseResponseCode){
        return new DataResult(baseResponseCode);
    }

    public static <T> DataResult getResult(BaseResponseCode baseResponseCode,T data){
        return new DataResult(baseResponseCode,data);
    }

    public static DataResult success(){
        return  new DataResult();
    }
}

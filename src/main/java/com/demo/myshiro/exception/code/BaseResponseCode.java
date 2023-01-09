package com.demo.myshiro.exception.code;

public enum BaseResponseCode implements ResponseCodeInterface{

    SUCCESS("0","操作成功"),
    SYSTEM_ERROR("5000001","系统异常稍后重试"),
    DATA_ERROR("4000001","传入数据异常"),
    ACCOUNT_ERROR("4000002","用户不存在"),
    ACCOUNT_LOCK("4000003","用户被锁定，请联系管理员"),
    ACCOUNT_PASSWORD_ERROR("4000004","密码错误")
    ;

    private final String code;

    private final String msg;

    BaseResponseCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

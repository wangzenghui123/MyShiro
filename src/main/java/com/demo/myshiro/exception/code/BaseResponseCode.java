package com.demo.myshiro.exception.code;

public enum BaseResponseCode implements ResponseCodeInterface{

    SUCCESS("0","操作成功"),
    SYSTEM_ERROR("5000001","系统异常稍后重试"),
    DATA_ERROR("4000001","传入数据异常"),
    ACCOUNT_ERROR("4000002","用户不存在"),
    ACCOUNT_LOCK_TIP("4000003","用户被锁定，请联系管理员"),
    ACCOUNT_PASSWORD_ERROR("4000004","密码错误"),
    TOKEN_NOT_NULL("4010001","认证token不能为空，请登录获取"),
    TOKEN_ERROR("4010001","token认证失败，请重新登录获取token"),
    ACCOUNT_LOCK("4010001","账号被锁定，联系管理员"),
    ACCOUNT_HAS_DELETED_ERROR("4010001","账号已被删除，请联系管理员"),
    TOKEN_PAST_DUE("4010002","token失效，请刷新token"),

    NOT_PERMISSION("4030001","没有权限访问资源")
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

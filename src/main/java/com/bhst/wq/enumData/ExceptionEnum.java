package com.bhst.wq.enumData;


public enum ExceptionEnum {

    SUCCESS(200, "成功"),
    RUNTIMEEXCEPTION(500, "系统异常"),
    NOLOGIN(1000, "未登录"),
    FAILURELOGIN(1001, "登陆失败"),
    NORIGHTS(1002, "权限不足"),
    BUSINESS(1002, "业务异常");

    private Integer code;
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

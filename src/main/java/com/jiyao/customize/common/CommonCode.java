package com.jiyao.customize.common;

/**
 * Created by mrt on 2018/3/5.
 * 10000-- 通用错误代码
 * 22000-- 媒资错误代码
 * 23000-- 用户中心错误代码
 * 24000-- cms错误代码
 * 25000-- 文件系统
 */
public enum CommonCode implements ResultCode {
    /**
     * SUCCESS:成功
     * FAIL：失败
     */
    SUCCESS(10000,"操作成功！"),
    //---系统错误返回码-----
    FAIL(10001,"操作失败"),
    UNAUTHENTICATED(10002,"您还未登录"),
    UNAUTHORISE(10003,"权限不足"),

    SERVER_ERROR(99999,"抱歉，系统繁忙，请稍后重试！");

    int code;
    String message;

    CommonCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }

}

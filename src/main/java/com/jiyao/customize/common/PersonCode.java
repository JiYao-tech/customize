package com.jiyao.customize.common;


public enum PersonCode implements ResultCode {

    FAIL(20001,"添加人脸失败");

    int code;
    String message;

    PersonCode(int code, String message){
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

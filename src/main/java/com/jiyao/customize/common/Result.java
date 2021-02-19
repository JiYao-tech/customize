package com.jiyao.customize.common;

import lombok.Data;

/**
 * @author 36536
 */
@Data
public class Result {

    private Integer code;
    private String message;
    private Object data;

    /**
     * 请求成功的响应，不带查询数据（用于删除、修改、新增接口）
     * @param code
     */
    public Result(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }

    /**
     * 请求成功的响应，带有查询数据（用于数据查询接口）
     * @param resCode
     * @param data
     */
    public Result(ResultCode resCode, Object data) {
        this.code = resCode.code();
        this.message = resCode.message();
        this.data = data;
    }


    public static Result SUCCESS(){
        return new Result(CommonCode.SUCCESS);
    }

    public static Result ERROR(){
        return new Result(CommonCode.SERVER_ERROR);
    }

    public static Result FAIL(){
        return new Result(CommonCode.FAIL);
    }

    public static Result FAIL_MSG(ResultCode resCode, String msg){
        return new Result(resCode,msg);
    }
}

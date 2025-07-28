package com.example.vms.common;

import lombok.Data;

@Data
public class Result {


    private int code;// 200 、400
    private String msg;//成功 失败
    private long total;//记录查询总数
    private Object Data;//返回数据


// 定义一个无参构造方法
    public Result() {
    // 构造方法体为空
    }

    public Result(int code, String msg, long total, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        Data = data;
    }

    // 查询成功返回结果
    public static Result success(long total,Object data){
        return new Result(200,"查询成功",total,data);
    }
    public static Result success(Object data){
        return new Result(200,"查询成功",0l,data);
    }
    public static Result success( ){
        return new Result(200,"添加成功",0l,null);
    }



    // 失败返回结果
    public static Result fail(){

        return new Result(400,"失败",0l,null);
    }


}

package com.example.ylb.common.result;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

@Data
public class JsonResult {


    private Boolean success = true;//响应成功标识
    private String msg;//提示信息
    private Object data;//其他数据

    public static JsonResult me(){
        return new JsonResult();
    }

    public JsonResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        this.success = false;
        return this;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }
}

package com.example.ylb.common.exception;


/*
*
* 业务异常类
* */
public class BusinessException  extends RuntimeException{


    public BusinessException(String messgae){
        super(messgae);
    }

    public BusinessException(){
        super();
    }
}

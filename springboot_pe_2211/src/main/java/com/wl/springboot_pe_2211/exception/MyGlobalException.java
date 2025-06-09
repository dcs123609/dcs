package com.wl.springboot_pe_2211.exception;

import lombok.Data;

import java.io.FileInputStream;

/**
 * 自定义异常
 * 继承 RuntimeException 运行时异常
 * 继承 Exception  编译时异常      在编译阶段就需要对异常进行处理
 */

@Data
public class MyGlobalException extends RuntimeException {

    //定义一个属性用来存储提示消息
    private String message;

    //提供有参构造   创建对象并给属性进行赋值
    public MyGlobalException(GlobalExceptionEnum globalExceptionEnum) {
        this.message = globalExceptionEnum.getMessage();
    }

    public static void main(String[] args) {
        MyGlobalException myGlobalException = new MyGlobalException(GlobalExceptionEnum.UPLOAD_ERROR);
        System.out.println("myGlobalException = " + myGlobalException);
    }

}

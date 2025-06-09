package com.wl.springboot_pe_2211.exception;


import com.wl.springboot_pe_2211.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 */

@RestControllerAdvice
public class MyExceptionHandler {




    @ExceptionHandler(MyGlobalException.class)
    public R exceptionHandler(MyGlobalException e) {
        //打印错误消息到控制台
        e.printStackTrace();
        //返回给前台error，并且提示消息就是异常的消息
        return R.err(e.getMessage());
    }


    //如果出现Exception异常，统一处理
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        e.printStackTrace();
        return R.err(GlobalExceptionEnum.SYSTEM_ERROR.getMessage());
    }
}

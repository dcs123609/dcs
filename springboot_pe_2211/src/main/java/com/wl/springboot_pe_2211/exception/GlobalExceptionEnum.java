package com.wl.springboot_pe_2211.exception;

import lombok.Data;

/**
 * 自定义异常的枚举类
 */


public enum GlobalExceptionEnum {
    DELETE_ERROR("删除数据异常"),
    UPDATE_ERROR("修改数据异常"),
    PARAMS_ERROR("参数检验出现异常"),
    UPLOAD_ERROR("上传文件出现异常"),
    SYSTEM_ERROR("系统异常，请重试......."),
    UPLOAD_NOT_NULL("上传文件不能为空"),
    UPLOAD_PIC_TYPE_ERROR("上传的图片格式不对，只能是jpg或者png");


    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    GlobalExceptionEnum(String message) {
        this.message = message;
    }


}

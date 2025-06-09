package com.wl.springboot_pe_2211.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * R统一返回格式
 * 标记
 * 消息
 * 数据
 *
 */

@Data
public class R<T> implements Serializable {
    //标记
    private Boolean flag = true;
    //消息
    private String message;
    //数据
    private T data;
    // 成功 只有标记
    public static R success() {
        return new R();
    }

    //成功   标记和消息
    public static R success(String message) {
        R r = new R();
        r.setMessage(message);
        return r;
    }

    // 成功  标记 消息  数据
    public static <T> R<T> success(String message,T data) {
        R<T> r = new R<>();
        r.setMessage(message);
        r.setData(data);
        return r;
    }
    //失败   标记和消息
    public static R err(String message) {
        R r = new R();
        r.setFlag(false);
        r.setMessage(message);
        return r;
    }
    //失败   标记
    public static R err() {
        R r = new R();
        r.setFlag(false);
        return r;
    }


}

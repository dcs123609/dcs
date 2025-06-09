package com.wl.springboot_pe_2211.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页返回结果
 *
 * 5   1
 *
 * 检查项     List<checkitem>
 *     检查组
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    //总条数
    private Long total;
    //分页返回的结果
    private List<T> rows;
}

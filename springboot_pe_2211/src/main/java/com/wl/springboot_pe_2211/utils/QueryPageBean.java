package com.wl.springboot_pe_2211.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装前台传递过来的分析参数
 */
@Data
public class QueryPageBean implements Serializable {

    // where group by  order by limit having


    //select * from t_checkitem where 条件  limit ?,?

    //当前页
    private Integer currentPage;
    //每页显示的条数
    private Integer pageSize;
    //条件
    private String queryString;
}

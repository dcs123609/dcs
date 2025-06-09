package com.wl.springboot_pe_2211.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OrderInfo implements Serializable {

    private Integer setmealId;
    private String sex;
    private String name;
    private String telephone;
    private String idCard;
    //json日期类型转换
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
}

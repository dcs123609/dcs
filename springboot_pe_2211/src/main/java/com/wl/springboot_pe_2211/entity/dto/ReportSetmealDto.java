package com.wl.springboot_pe_2211.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportSetmealDto implements Serializable {

    private String name;
    private Integer value;
}

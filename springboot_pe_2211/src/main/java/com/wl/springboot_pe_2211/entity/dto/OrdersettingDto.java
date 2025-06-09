package com.wl.springboot_pe_2211.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersettingDto {
    private Integer date;
    private Integer number;
    private Integer reservations;
}

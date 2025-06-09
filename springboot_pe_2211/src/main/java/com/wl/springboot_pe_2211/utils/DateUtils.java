package com.wl.springboot_pe_2211.utils;

public class DateUtils {

    /**
     * 判断日期的格式和是否为空
     */
    public static Boolean validateOrderDate(String orderDate) {
        if (orderDate == null) {
            return false;
        }
        //yyyy-MM
        String reg = "\\d{4}-\\d{2}";
        return orderDate.matches(reg);
    }
}

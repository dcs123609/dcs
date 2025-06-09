package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.service.IMemberService;
import com.wl.springboot_pe_2211.service.IOrderService;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Resource
    private IMemberService memberService;
    @Resource
    private IOrderService orderService;

    @GetMapping("getMemberReport")
    public R getMemberReport() {
        return memberService.getMemberReport();
    }

    @GetMapping("getSetmealReport")
    public R getSetmealReport() {
        return orderService.getSetmealReport();
    }
}

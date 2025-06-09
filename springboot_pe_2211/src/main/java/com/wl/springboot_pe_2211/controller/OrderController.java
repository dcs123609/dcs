package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.entity.dto.OrderInfo;
import com.wl.springboot_pe_2211.service.IOrderService;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-16
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 立即预约
     * @param orderInfo
     * @return
     */
    @PostMapping("/submitOrder")
    public R submitOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.submitOrder(orderInfo);
    }
}


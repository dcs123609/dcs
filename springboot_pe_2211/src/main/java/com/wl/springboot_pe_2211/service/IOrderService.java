package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.springboot_pe_2211.entity.dto.OrderInfo;
import com.wl.springboot_pe_2211.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-16
 */
public interface IOrderService extends IService<Order> {

    R submitOrder(OrderInfo orderInfo);

    R getSetmealReport();
}

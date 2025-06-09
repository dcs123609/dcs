package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.Ordersetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.springboot_pe_2211.utils.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-22
 */
public interface IOrdersettingService extends IService<Ordersetting> {

    R addOrderSetting(List<String[]> stringList);

    R findByYearAndMonth(String orderDate);

    R updateNumberByOrderDate(Ordersetting ordersetting);
}

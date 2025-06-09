package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.SetmealCheckgroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.springboot_pe_2211.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
public interface ISetmealCheckgroupService extends IService<SetmealCheckgroup> {

    R findCheckGroupBySetMealId(Integer id);
}

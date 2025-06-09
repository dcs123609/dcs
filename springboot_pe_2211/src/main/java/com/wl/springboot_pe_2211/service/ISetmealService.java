package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
public interface ISetmealService extends IService<Setmeal> {

    R addOrUpdateSetmeal(Setmeal setmeal, Integer[] ids);

    R<PageResult<Setmeal>> findPage(QueryPageBean queryPageBean);

    R deleteById(Integer id);

    R<List<Setmeal>> findAll();

    R<Setmeal> findSetMealInfoById(Integer id);
}

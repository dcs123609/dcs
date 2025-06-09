package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.Checkitem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
public interface ICheckitemService extends IService<Checkitem> {

    R addCheckItem(Checkitem checkitem);

    R<PageResult<Checkitem>> findPage(QueryPageBean queryPageBean);

    R deleteById(Integer id);

    R updateCheckItem(Checkitem checkitem);

    R findAll();
}

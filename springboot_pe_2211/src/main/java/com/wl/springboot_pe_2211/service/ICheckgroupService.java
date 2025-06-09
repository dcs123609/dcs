package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.Checkgroup;
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
public interface ICheckgroupService extends IService<Checkgroup> {

    R addCheckGroup(Checkgroup checkgroup, Integer[] checkitemIds);

    R<PageResult<Checkgroup>> findPage(QueryPageBean queryPageBean);

    R deleteById(Integer id);

    R updateCheckGroup(Checkgroup checkgroup, Integer[] checkitemIds);

    R findAllCheckGroup();
}

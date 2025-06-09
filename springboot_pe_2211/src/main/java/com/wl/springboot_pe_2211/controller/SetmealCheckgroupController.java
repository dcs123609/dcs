package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.service.ISetmealCheckgroupService;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
@RestController
@RequestMapping("/setmealCheckgroup")
public class SetmealCheckgroupController {

    @Resource
    private ISetmealCheckgroupService setmealCheckgroupService;

    /**
     * 通过setmealId查询所有的checkGroupId
     */
    @GetMapping("/findCheckGroupBySetMealId/{id}")
    public R findCheckGroupBySetMealId(@PathVariable Integer id) {
        return setmealCheckgroupService.findCheckGroupBySetMealId(id);
    }

}


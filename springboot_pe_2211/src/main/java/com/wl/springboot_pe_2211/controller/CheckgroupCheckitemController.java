package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.service.ICheckgroupCheckitemService;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/checkgroupCheckitem")
public class CheckgroupCheckitemController {

    @Resource
    private ICheckgroupCheckitemService checkgroupCheckitemService;

    @GetMapping("findCheckItemIdByCheckGroupId/{id}")
    public R<List<Integer>> findCheckItemIdByCheckGroupId(@PathVariable Integer id) {
        return checkgroupCheckitemService.findCheckItemIdByCheckGroupId(id);

    }

}


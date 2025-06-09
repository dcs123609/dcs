package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.entity.Checkitem;
import com.wl.springboot_pe_2211.service.ICheckitemService;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/checkItem")
@Api(tags = "检查项模块")
public class CheckitemController {



    @Resource
    private ICheckitemService checkitemService;

    /**
     * 添加检查项
     * @param checkitem
     * @return
     */
    @PostMapping("addCheckItem")
    @ApiOperation(value = "添加检查项的方法")
    public R addCheckItem(@RequestBody Checkitem checkitem) {
        return checkitemService.addCheckItem(checkitem);
    }


    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("findPage")
    @ApiOperation(value = "分页查询检查项的方法")
    public R<PageResult<Checkitem>> findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkitemService.findPage(queryPageBean);
    }

    /**
     * 删除检查项
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    @ApiOperation(value = "删除检查项的方法")
    public R deleteById(@PathVariable Integer id) {
        return checkitemService.deleteById(id);
    }

    /**
     * 修改检查项
     * @param checkitem
     * @return
     */
    @PutMapping("updateCheckItem")
    @ApiOperation(value = "修改检查项的方法")
    public R updateCheckItem(@RequestBody Checkitem checkitem) {
        return checkitemService.updateCheckItem(checkitem);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("findAll")
    @ApiOperation(value = "查询所有检查项的方法")
    public R findAll() {
        return checkitemService.findAll();
    }
}


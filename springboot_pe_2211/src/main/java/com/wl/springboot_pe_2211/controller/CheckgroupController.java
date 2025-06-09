package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.entity.Checkgroup;
import com.wl.springboot_pe_2211.service.ICheckgroupService;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/checkGroup")
public class CheckgroupController {


    @Resource
    private ICheckgroupService checkgroupService;

    /**
     * 添加检查组
     * @param checkgroup   检查组的信息
     * @param checkitemIds  勾选的检查项的id集合
     * @return
     */
    @PostMapping("addCheckGroup")
    public R addCheckGroup(@RequestBody Checkgroup checkgroup, Integer[] checkitemIds) {
        return checkgroupService.addCheckGroup(checkgroup,checkitemIds);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("findPage")
    public R<PageResult<Checkgroup>> findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkgroupService.findPage(queryPageBean);
    }

    /**
     * 删除检查组
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public R deleteById(@PathVariable Integer id) {
        return checkgroupService.deleteById(id);
    }

    /**
     * 修改检查组
     * @param checkgroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("updateCheckGroup/{checkitemIds}")
    public R updateCheckGroup(@RequestBody Checkgroup checkgroup,@PathVariable Integer[] checkitemIds) {
        return checkgroupService.updateCheckGroup(checkgroup,checkitemIds);
    }

    @GetMapping("findAllCheckGroup")
    public R findAllCheckGroup() {
        return checkgroupService.findAllCheckGroup();
    }
}


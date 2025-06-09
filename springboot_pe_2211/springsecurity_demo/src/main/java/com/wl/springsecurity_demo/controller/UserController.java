package com.wl.springsecurity_demo.controller;


import com.wl.springsecurity_demo.utils.ResultCode;
import com.wl.springsecurity_demo.utils.ResultData;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('user:list')")
    public ResultData userList() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户查询界面成功!");
    }

    @GetMapping("/add")
    //ROLE_admin 只有ROLE_admin角色才能访问
    @Secured("ROLE_admin")
    public ResultData userAdd() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户新增界面成功!");
    }
    /**
     * 测试无权限访问，数据库中权限是user:update
     * @return
     */
    @GetMapping("/update")
    //需要user:update权限才能访问      menu ----perms字段
    @PreAuthorize("hasAnyAuthority('user:update')")
    public ResultData userUpdate() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户修改界面成功!");
    }
    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResultData userDelete() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户删除界面成功!");
    }



}


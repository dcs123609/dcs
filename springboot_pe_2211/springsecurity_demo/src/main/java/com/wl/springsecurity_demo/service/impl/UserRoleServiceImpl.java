package com.wl.springsecurity_demo.service.impl;

import com.wl.springsecurity_demo.pojo.UserRole;
import com.wl.springsecurity_demo.mapper.UserRoleMapper;
import com.wl.springsecurity_demo.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}

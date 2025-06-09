package com.wl.springsecurity_demo.service.impl;

import com.wl.springsecurity_demo.pojo.Role;
import com.wl.springsecurity_demo.mapper.RoleMapper;
import com.wl.springsecurity_demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

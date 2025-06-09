package com.wl.springsecurity_demo.service.impl;

import com.wl.springsecurity_demo.pojo.User;
import com.wl.springsecurity_demo.mapper.UserMapper;
import com.wl.springsecurity_demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

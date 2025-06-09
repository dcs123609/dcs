package com.wl.springsecurity_demo.service.impl;

import com.wl.springsecurity_demo.pojo.Menu;
import com.wl.springsecurity_demo.mapper.MenuMapper;
import com.wl.springsecurity_demo.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}

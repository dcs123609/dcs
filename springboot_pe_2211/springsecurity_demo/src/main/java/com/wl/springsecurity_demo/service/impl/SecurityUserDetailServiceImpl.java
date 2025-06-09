package com.wl.springsecurity_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wl.springsecurity_demo.mapper.MenuMapper;
import com.wl.springsecurity_demo.mapper.RoleMapper;
import com.wl.springsecurity_demo.mapper.UserMapper;
import com.wl.springsecurity_demo.pojo.Menu;
import com.wl.springsecurity_demo.pojo.Role;
import com.wl.springsecurity_demo.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityUserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;


    //通过用户名 将用户的密码 角色 权限 都从数据库中查询出来，交给springSecurity
    //至于框架拿到密码 角色 权限怎么验证  那是框架的事情
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //1.先通过用户名在用户表查询信息  select * from user where username = ?
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new RuntimeException("没有该用户信息");
        }
        //查询对应的角色
        List<Role> roleList = roleMapper.findRoleByUserId(user.getId());
        // //存储当前用户对应的角色和权限
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (Role role : roleList) {
            //存储的是role_code 角色的编码  前面拼接ROLE_
            String roleCode = "ROLE_" + role.getRoleCode();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleCode);
            grantedAuthorityList.add(grantedAuthority);
        }

        ////3、查询对应的权限
        List<Menu> menuList = menuMapper.findMenuByUserId(user.getId());
        for (Menu menu : menuList) {
            String perms = menu.getPerms();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(perms);
            grantedAuthorityList.add(grantedAuthority);
        }

        //把账号名  密码  权限  角色交给框架就可以了
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),grantedAuthorityList);


    }
}

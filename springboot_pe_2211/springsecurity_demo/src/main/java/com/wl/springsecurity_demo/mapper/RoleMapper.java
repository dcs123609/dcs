package com.wl.springsecurity_demo.mapper;

import com.wl.springsecurity_demo.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
public interface RoleMapper extends BaseMapper<Role> {


    @Select("SELECT * FROM sys_user u\n" +
            "INNER JOIN sys_user_role ur\n" +
            "INNER JOIN sys_role r\n" +
            "ON u.id = ur.user_id\n" +
            "AND r.id = ur.role_id\n" +
            "WHERE u.id = #{id}")
    List<Role> findRoleByUserId(Integer id);
}

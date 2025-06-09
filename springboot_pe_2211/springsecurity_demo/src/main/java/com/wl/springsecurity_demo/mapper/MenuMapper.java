package com.wl.springsecurity_demo.mapper;

import com.wl.springsecurity_demo.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author 风中少年
 * @since 2025-06-05
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT m.* FROM sys_user u\n" +
            "INNER JOIN sys_user_role ur\n" +
            "INNER JOIN sys_role r\n" +
            "INNER JOIN sys_role_menu rm\n" +
            "INNER JOIN sys_menu m\n" +
            "ON u.id = ur.user_id\n" +
            "AND r.id = ur.role_id\n" +
            "AND r.id = rm.role_id\n" +
            "AND rm.menu_id = m.id\n" +
            "WHERE u.id = #{id}")
    List<Menu> findMenuByUserId(Integer id);
}

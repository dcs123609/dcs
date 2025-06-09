package com.wl.springboot_pe_2211.mapper;

import com.wl.springboot_pe_2211.entity.Checkgroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
public interface CheckgroupMapper extends BaseMapper<Checkgroup> {

    @Select("SELECT c.* FROM t_checkgroup c\n" +
            "INNER JOIN t_setmeal_checkgroup sc\n" +
            "ON c.id = sc.checkgroup_id\n" +
            "WHERE sc.setmeal_id = #{id}")
    List<Checkgroup> findCheckGroupById(Integer id);
}

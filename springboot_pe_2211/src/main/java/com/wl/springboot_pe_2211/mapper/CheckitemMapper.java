package com.wl.springboot_pe_2211.mapper;

import com.wl.springboot_pe_2211.entity.Checkitem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
public interface CheckitemMapper extends BaseMapper<Checkitem> {


    @Select("SELECT c.* \n" +
            "FROM t_checkitem c\n" +
            "INNER JOIN t_checkgroup_checkitem cc\n" +
            "ON c.id = cc.checkitem_id\n" +
            "WHERE cc.checkgroup_id = #{checkgroupId}")
    List<Checkitem> findCheckItemById(Integer checkgroupId);
}

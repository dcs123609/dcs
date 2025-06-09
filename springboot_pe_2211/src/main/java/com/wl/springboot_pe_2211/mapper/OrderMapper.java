package com.wl.springboot_pe_2211.mapper;

import com.wl.springboot_pe_2211.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wl.springboot_pe_2211.entity.dto.ReportSetmealDto;
import com.wl.springboot_pe_2211.utils.R;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-16
 */
public interface OrderMapper extends BaseMapper<Order> {


    @Select("SELECT s.name as name,COUNT(*) AS value FROM t_order o INNER JOIN t_setmeal s \n" +
            "ON s.id = o.setmeal_id GROUP BY s.name")
    List<ReportSetmealDto> getSetmealReport();
}

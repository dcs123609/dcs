package com.wl.springboot_pe_2211.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_setmeal_checkgroup")
public class SetmealCheckgroup implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer setmealId;

    private Integer checkgroupId;


}

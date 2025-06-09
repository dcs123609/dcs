package com.wl.springboot_pe_2211.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_checkgroup_checkitem")
public class CheckgroupCheckitem implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer checkgroupId;

    private Integer checkitemId;


}

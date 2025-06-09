package com.wl.springboot_pe_2211.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("t_checkitem")
@ApiModel(value = "检查项的实体类")
public class Checkitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键",required = false)
    private Integer id;
    @ApiModelProperty(value = "项目编码",required = false)
    private String code;
    @ApiModelProperty(value = "名称",required = false)
    private String name;
    @ApiModelProperty(value = "性别",required = false)
    private String sex;
    @ApiModelProperty(value = "年龄",required = false)
    private String age;
    @ApiModelProperty(value = "价格",required = false)
    private Float price;

    /**
     * 查检项类型,分为检查和检验两种
     */
    @ApiModelProperty(value = "查检项类型,分为检查和检验两种",required = false)
    private String type;

    @ApiModelProperty(value = "描述",required = false)
    private String attention;
    @ApiModelProperty(value = "注意事项",required = false)
    private String remark;

    //逻辑删除的字段
    @TableLogic(value = "1",delval = "0")
    @ApiModelProperty(value = "逻辑删除的字段",required = false)
    private Integer deleted;
}

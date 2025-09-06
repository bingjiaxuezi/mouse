package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 实验对象 t_experiment
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TExperiment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 实验ID */
    private Long id;

    /** 实验名称 */
    @Excel(name = "实验名称")
    private String experimentName;

    /** 实验编号 */
    @Excel(name = "实验编号")
    private String experimentCode;

    /** 实验描述 */
    @Excel(name = "实验描述")
    private String description;

    /** 研究员ID */
    @Excel(name = "研究员ID")
    private Long researcherId;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
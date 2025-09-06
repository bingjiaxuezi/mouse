package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生理状态对象 t_physiological_state
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TPhysiologicalState extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 生理状态ID */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceCode;

    /** 老鼠编号 */
    @Excel(name = "老鼠编号")
    private String mouseCode;

    /** 实验ID */
    @Excel(name = "实验ID")
    private Long experimentId;

    /** 状态类型 */
    @Excel(name = "状态类型")
    private String stateType;

    /** 状态值 */
    @Excel(name = "状态值")
    private String stateValue;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 置信度 */
    @Excel(name = "置信度")
    private BigDecimal confidence;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
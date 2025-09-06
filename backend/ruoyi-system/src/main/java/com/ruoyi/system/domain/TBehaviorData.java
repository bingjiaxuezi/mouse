package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 行为数据对象 t_behavior_data
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TBehaviorData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 行为数据ID */
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

    /** 行为类型 */
    @Excel(name = "行为类型")
    private String behaviorType;

    /** 行为值（JSON格式） */
    @Excel(name = "行为值")
    private String behaviorValue;

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
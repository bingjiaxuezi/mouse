package com.ruoyi.system.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生理状态视图对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TPhysiologicalStateVO {
    
    /** 生理状态ID */
    private Long id;

    /** 设备编号 */
    private String deviceCode;

    /** 设备名称 */
    private String deviceName;

    /** 老鼠编号 */
    private String mouseCode;

    /** 老鼠名称 */
    private String mouseName;

    /** 实验ID */
    private Long experimentId;

    /** 实验名称 */
    private String experimentName;

    /** 状态类型 */
    private String stateType;

    /** 状态值 */
    private String stateValue;

    /** 单位 */
    private String unit;

    /** 置信度 */
    private BigDecimal confidence;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 备注 */
    private String remark;
}
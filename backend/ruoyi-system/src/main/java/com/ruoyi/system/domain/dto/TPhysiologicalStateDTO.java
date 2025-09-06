package com.ruoyi.system.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 生理状态传输对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TPhysiologicalStateDTO {
    
    /** 生理状态ID */
    private Long id;

    /** 设备编号 */
    @NotBlank(message = "设备编号不能为空")
    @Size(max = 50, message = "设备编号长度不能超过50个字符")
    private String deviceCode;

    /** 老鼠编号 */
    @Size(max = 50, message = "老鼠编号长度不能超过50个字符")
    private String mouseCode;

    /** 实验ID */
    private Long experimentId;

    /** 状态类型 */
    @Size(max = 50, message = "状态类型长度不能超过50个字符")
    private String stateType;

    /** 状态值 */
    private String stateValue;

    /** 单位 */
    @Size(max = 20, message = "单位长度不能超过20个字符")
    private String unit;

    /** 置信度 */
    private BigDecimal confidence;

    /** 时间戳 */
    @NotNull(message = "时间戳不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 备注 */
    @Size(max = 500, message = "备注长度不能超过500个字符")
    private String remark;
}
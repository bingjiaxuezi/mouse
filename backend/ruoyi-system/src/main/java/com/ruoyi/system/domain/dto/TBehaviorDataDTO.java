package com.ruoyi.system.domain.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 行为数据传输对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TBehaviorDataDTO {
    
    /** 行为数据ID */
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

    /** 行为类型 */
    @Size(max = 50, message = "行为类型长度不能超过50个字符")
    private String behaviorType;

    /** 行为值（JSON格式） */
    private String behaviorValue;

    /** 置信度 */
    private BigDecimal confidence;

    /** 时间戳 */
    @NotNull(message = "时间戳不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
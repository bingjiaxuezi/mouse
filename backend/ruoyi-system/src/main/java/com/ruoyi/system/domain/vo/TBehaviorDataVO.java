package com.ruoyi.system.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.enums.BehaviorTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 行为数据视图对象
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Data
public class TBehaviorDataVO {
    
    /** 行为数据ID */
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

    /** 行为类型 */
    private String behaviorType;
    
    /** 行为类型中文名称 */
    private String behaviorTypeName;

    /** 行为值（JSON格式） */
    private String behaviorValue;
    
    /** 格式化后的行为值 */
    private String formattedBehaviorValue;

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
    
    /**
     * 获取行为类型中文名称
     * @return 中文名称
     */
    public String getBehaviorTypeName() {
        if (behaviorTypeName == null && behaviorType != null) {
            behaviorTypeName = BehaviorTypeEnum.getNameByCode(behaviorType);
        }
        return behaviorTypeName;
    }
    
    /**
     * 获取格式化后的行为值
     * @return 格式化后的行为值
     */
    public String getFormattedBehaviorValue() {
        if (formattedBehaviorValue == null && behaviorValue != null) {
            formattedBehaviorValue = formatBehaviorValue(behaviorValue, behaviorType);
        }
        return formattedBehaviorValue;
    }
    
    /**
     * 格式化行为值
     * @param value 原始行为值
     * @param type 行为类型
     * @return 格式化后的值
     */
    private String formatBehaviorValue(String value, String type) {
        if (value == null || value.trim().isEmpty()) {
            return "-";
        }
        
        // 如果是数字，尝试添加单位
        try {
            double numValue = Double.parseDouble(value);
            BehaviorTypeEnum behaviorEnum = BehaviorTypeEnum.getByCode(type);
            if (behaviorEnum != null) {
                String unit = behaviorEnum.getUnit();
                if ("分钟".equals(unit)) {
                    return String.format("%.1f分钟", numValue);
                } else if ("次数".equals(unit)) {
                    return String.format("%.0f次", numValue);
                } else if ("米".equals(unit)) {
                    return String.format("%.1f米", numValue);
                }
            }
            return String.format("%.1f", numValue);
        } catch (NumberFormatException e) {
            // 不是数字，直接返回原值
            return value;
        }
    }
}
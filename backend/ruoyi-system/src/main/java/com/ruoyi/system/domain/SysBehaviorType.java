package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行为类型字典对象 sys_behavior_type
 * 
 * @author lczj
 * @date 2025-09-20
 */
public class SysBehaviorType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行为类型ID */
    private Long behaviorTypeId;

    /** 行为编码 */
    @Excel(name = "行为编码")
    private String behaviorCode;

    /** 行为名称 */
    @Excel(name = "行为名称")
    private String behaviorName;

    /** 行为分类 */
    @Excel(name = "行为分类")
    private String behaviorCategory;

    /** 行为描述 */
    @Excel(name = "行为描述")
    private String description;

    /** 检测方法 */
    @Excel(name = "检测方法")
    private String detectionMethod;

    /** AI模型版本 */
    @Excel(name = "AI模型版本")
    private String aiModelVersion;

    /** 置信度阈值 */
    @Excel(name = "置信度阈值")
    private BigDecimal confidenceThreshold;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String isActive;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortOrder;

    /** 颜色代码 */
    @Excel(name = "颜色代码")
    private String colorCode;

    /** 图标URL */
    @Excel(name = "图标URL")
    private String iconUrl;

    /** 删除标志 */
    private String delFlag;

    /** 扩展信息(JSON格式) */
    private String extendInfo;

    /** 扩展配置(JSON格式) */
    private String extendConfig;

    public void setBehaviorTypeId(Long behaviorTypeId) 
    {
        this.behaviorTypeId = behaviorTypeId;
    }

    public Long getBehaviorTypeId() 
    {
        return behaviorTypeId;
    }

    public void setBehaviorCode(String behaviorCode) 
    {
        this.behaviorCode = behaviorCode;
    }

    public String getBehaviorCode() 
    {
        return behaviorCode;
    }

    public void setBehaviorName(String behaviorName) 
    {
        this.behaviorName = behaviorName;
    }

    public String getBehaviorName() 
    {
        return behaviorName;
    }

    public void setBehaviorCategory(String behaviorCategory) 
    {
        this.behaviorCategory = behaviorCategory;
    }

    public String getBehaviorCategory() 
    {
        return behaviorCategory;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDetectionMethod(String detectionMethod) 
    {
        this.detectionMethod = detectionMethod;
    }

    public String getDetectionMethod() 
    {
        return detectionMethod;
    }

    public void setAiModelVersion(String aiModelVersion) 
    {
        this.aiModelVersion = aiModelVersion;
    }

    public String getAiModelVersion() 
    {
        return aiModelVersion;
    }

    public void setConfidenceThreshold(BigDecimal confidenceThreshold) 
    {
        this.confidenceThreshold = confidenceThreshold;
    }

    public BigDecimal getConfidenceThreshold() 
    {
        return confidenceThreshold;
    }

    public void setIsActive(String isActive) 
    {
        this.isActive = isActive;
    }

    public String getIsActive() 
    {
        return isActive;
    }

    public void setSortOrder(Long sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder() 
    {
        return sortOrder;
    }

    public void setColorCode(String colorCode) 
    {
        this.colorCode = colorCode;
    }

    public String getColorCode() 
    {
        return colorCode;
    }

    public void setIconUrl(String iconUrl) 
    {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() 
    {
        return iconUrl;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public void setExtendInfo(String extendInfo) 
    {
        this.extendInfo = extendInfo;
    }

    public String getExtendInfo() 
    {
        return extendInfo;
    }

    public void setExtendConfig(String extendConfig) 
    {
        this.extendConfig = extendConfig;
    }

    public String getExtendConfig() 
    {
        return extendConfig;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("behaviorTypeId", getBehaviorTypeId())
            .append("behaviorCode", getBehaviorCode())
            .append("behaviorName", getBehaviorName())
            .append("behaviorCategory", getBehaviorCategory())
            .append("description", getDescription())
            .append("detectionMethod", getDetectionMethod())
            .append("aiModelVersion", getAiModelVersion())
            .append("confidenceThreshold", getConfidenceThreshold())
            .append("isActive", getIsActive())
            .append("sortOrder", getSortOrder())
            .append("colorCode", getColorCode())
            .append("iconUrl", getIconUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("extendInfo", getExtendInfo())
            .append("extendConfig", getExtendConfig())
            .toString();
    }
}

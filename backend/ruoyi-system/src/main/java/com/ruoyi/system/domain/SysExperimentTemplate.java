package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验模板对象 sys_experiment_template
 * 
 * @author lichenzijia
 * @date 2025-09-07
 */
public class SysExperimentTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板ID */
    private Long templateId;

    /** 模板编号 */
    @Excel(name = "模板编号")
    private String templateCode;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板类型 */
    @Excel(name = "模板类型")
    private String templateType;

    /** 模板描述 */
    @Excel(name = "模板描述")
    private String templateDesc;

    /** 默认实验周期(天) */
    @Excel(name = "默认实验周期(天)")
    private Long defaultDuration;

    /** 默认配置参数(JSON格式) */
    @Excel(name = "默认配置参数(JSON格式)")
    private String defaultConfig;

    /** 必需设备列表(JSON格式) */
    @Excel(name = "必需设备列表(JSON格式)")
    private String requiredEquipment;

    /** 模板状态（0启用 1禁用） */
    @Excel(name = "模板状态", readConverterExp = "0=启用,1=禁用")
    private String templateStatus;

    /** 创建者 */
    private String buildBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildTime;

    /** 更新者 */
    private String modifyBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setTemplateCode(String templateCode) 
    {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() 
    {
        return templateCode;
    }

    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }

    public void setTemplateType(String templateType) 
    {
        this.templateType = templateType;
    }

    public String getTemplateType() 
    {
        return templateType;
    }

    public void setTemplateDesc(String templateDesc) 
    {
        this.templateDesc = templateDesc;
    }

    public String getTemplateDesc() 
    {
        return templateDesc;
    }

    public void setDefaultDuration(Long defaultDuration) 
    {
        this.defaultDuration = defaultDuration;
    }

    public Long getDefaultDuration() 
    {
        return defaultDuration;
    }

    public void setDefaultConfig(String defaultConfig) 
    {
        this.defaultConfig = defaultConfig;
    }

    public String getDefaultConfig() 
    {
        return defaultConfig;
    }

    public void setRequiredEquipment(String requiredEquipment) 
    {
        this.requiredEquipment = requiredEquipment;
    }

    public String getRequiredEquipment() 
    {
        return requiredEquipment;
    }

    public void setTemplateStatus(String templateStatus) 
    {
        this.templateStatus = templateStatus;
    }

    public String getTemplateStatus() 
    {
        return templateStatus;
    }

    public void setBuildBy(String buildBy) 
    {
        this.buildBy = buildBy;
    }

    public String getBuildBy() 
    {
        return buildBy;
    }

    public void setBuildTime(Date buildTime) 
    {
        this.buildTime = buildTime;
    }

    public Date getBuildTime() 
    {
        return buildTime;
    }

    public void setModifyBy(String modifyBy) 
    {
        this.modifyBy = modifyBy;
    }

    public String getModifyBy() 
    {
        return modifyBy;
    }

    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateCode", getTemplateCode())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("templateDesc", getTemplateDesc())
            .append("defaultDuration", getDefaultDuration())
            .append("defaultConfig", getDefaultConfig())
            .append("requiredEquipment", getRequiredEquipment())
            .append("templateStatus", getTemplateStatus())
            .append("buildBy", getBuildBy())
            .append("buildTime", getBuildTime())
            .append("modifyBy", getModifyBy())
            .append("modifyTime", getModifyTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}

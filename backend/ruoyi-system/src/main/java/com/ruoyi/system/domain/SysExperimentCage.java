package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验笼子关系对象 sys_experiment_cage
 * 
 * @author ruoyi
 * @date 2025-09-07
 */
public class SysExperimentCage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long relationId;

    /** 实验ID */
    @Excel(name = "实验ID")
    private Long experimentId;

    /** 笼子ID */
    @Excel(name = "笼子ID")
    private Long cageId;

    /** 绑定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bindTime;

    /** 解绑时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "解绑时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date unbindTime;

    /** 绑定状态 */
    @Excel(name = "绑定状态")
    private String bindStatus;

    /** 绑定方式、 */
    @Excel(name = "绑定方式、")
    private String bindMethod;

    /** 绑定操作员 */
    @Excel(name = "绑定操作员")
    private String bindOperator;

    /** 笼子角色 */
    @Excel(name = "笼子角色")
    private String cageRole;

    /** 监控配置(JSON格式): */
    @Excel(name = "监控配置(JSON格式):")
    private String monitoringConfig;

    /** 创建者 */
    @Excel(name = "创建者")
    private String buildBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** 更新者 */
    private String modifyBy;

    public void setRelationId(Long relationId) 
    {
        this.relationId = relationId;
    }

    public Long getRelationId() 
    {
        return relationId;
    }

    public void setExperimentId(Long experimentId) 
    {
        this.experimentId = experimentId;
    }

    public Long getExperimentId() 
    {
        return experimentId;
    }

    public void setCageId(Long cageId) 
    {
        this.cageId = cageId;
    }

    public Long getCageId() 
    {
        return cageId;
    }

    public void setBindTime(Date bindTime) 
    {
        this.bindTime = bindTime;
    }

    public Date getBindTime() 
    {
        return bindTime;
    }

    public void setUnbindTime(Date unbindTime) 
    {
        this.unbindTime = unbindTime;
    }

    public Date getUnbindTime() 
    {
        return unbindTime;
    }

    public void setBindStatus(String bindStatus) 
    {
        this.bindStatus = bindStatus;
    }

    public String getBindStatus() 
    {
        return bindStatus;
    }

    public void setBindMethod(String bindMethod) 
    {
        this.bindMethod = bindMethod;
    }

    public String getBindMethod() 
    {
        return bindMethod;
    }

    public void setBindOperator(String bindOperator) 
    {
        this.bindOperator = bindOperator;
    }

    public String getBindOperator() 
    {
        return bindOperator;
    }

    public void setCageRole(String cageRole) 
    {
        this.cageRole = cageRole;
    }

    public String getCageRole() 
    {
        return cageRole;
    }

    public void setMonitoringConfig(String monitoringConfig) 
    {
        this.monitoringConfig = monitoringConfig;
    }

    public String getMonitoringConfig() 
    {
        return monitoringConfig;
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

    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    public void setModifyBy(String modifyBy) 
    {
        this.modifyBy = modifyBy;
    }

    public String getModifyBy() 
    {
        return modifyBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relationId", getRelationId())
            .append("experimentId", getExperimentId())
            .append("cageId", getCageId())
            .append("bindTime", getBindTime())
            .append("unbindTime", getUnbindTime())
            .append("bindStatus", getBindStatus())
            .append("bindMethod", getBindMethod())
            .append("bindOperator", getBindOperator())
            .append("cageRole", getCageRole())
            .append("monitoringConfig", getMonitoringConfig())
            .append("buildBy", getBuildBy())
            .append("buildTime", getBuildTime())
            .append("modifyTime", getModifyTime())
            .append("remark", getRemark())
            .append("modifyBy", getModifyBy())
            .toString();
    }
}

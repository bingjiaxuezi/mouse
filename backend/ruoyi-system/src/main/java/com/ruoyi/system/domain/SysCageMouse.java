package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 笼子-小鼠关系对象 sys_cage_mouse
 * 
 * @author lczj
 * @date 2025-09-14
 */
public class SysCageMouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long cageMouseId;

    /** 笼子ID */
    @Excel(name = "笼子ID")
    private Long cageId;

    /** 小鼠ID */
    @Excel(name = "小鼠ID")
    private Long mouseId;

    /** 迁入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "迁入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date moveInDate;

    /** 迁出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "迁出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date moveOutDate;

    /** 是否当前居住 */
    @Excel(name = "是否当前居住")
    private String isCurrent;

    /** 迁移原因 */
    @Excel(name = "迁移原因")
    private String moveReason;

    /** 笼内位置 */
    @Excel(name = "笼内位置")
    private String positionInCage;

    /** 优先级 */
    @Excel(name = "优先级")
    private Integer priorityLevel;

    /** 社会群组标识 */
    @Excel(name = "社会群组标识")
    private String socialGroup;

    /** 隔离标识 */
    @Excel(name = "隔离标识")
    private String isolationFlag;

    /** 喂食备注 */
    @Excel(name = "喂食备注")
    private String feedingSchedule;

    /** 监控级别： */
    @Excel(name = "监控级别：")
    private String monitoringLevel;

    /** 环境丰富化配置 */
    @Excel(name = "环境丰富化配置")
    private String environmentalEnrichment;

    /** 健康检查频率（天） */
    @Excel(name = "健康检查频率", readConverterExp = "天=")
    private Long healthCheckFrequency;

    /** 上次健康检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "上次健康检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastHealthCheck;

    /** 下次健康检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "下次健康检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextHealthCheck;

    /** 迁入时体重（克） */
    @Excel(name = "迁入时体重", readConverterExp = "克=")
    private BigDecimal weightAtMoveIn;

    /** 迁出时体重（克） */
    @Excel(name = "迁出时体重", readConverterExp = "克=")
    private BigDecimal weightAtMoveOut;

    /** 行为观察笔记 */
    @Excel(name = "行为观察笔记")
    private String behavioralNotes;

    /** 特殊护理说明 */
    @Excel(name = "特殊护理说明")
    private String specialCareInstructions;

    /** 删除标志 */
    private String delFlag;

    /** 扩展信息(JSON格式) */
    private String extendInfo;

    /** 扩展配置(JSON格式) */
    private String extendConfig;

    /** 扩展数据(JSON格式) */
    private String extendData;

    /** 扩展信息1(JSON格式) */
    private String extendInfo1;

    /** 扩展信息2(JSON格式) */
    private String extendInfo2;

    /** 扩展信息3(JSON格式) */
    private String extendInfo3;

    /** 扩展信息4(JSON格式) */
    private String extendInfo4;

    /** 扩展信息5(JSON格式) */
    private String extendInfo5;

    public void setCageMouseId(Long cageMouseId) 
    {
        this.cageMouseId = cageMouseId;
    }

    public Long getCageMouseId() 
    {
        return cageMouseId;
    }

    public void setCageId(Long cageId) 
    {
        this.cageId = cageId;
    }

    public Long getCageId() 
    {
        return cageId;
    }

    public void setMouseId(Long mouseId) 
    {
        this.mouseId = mouseId;
    }

    public Long getMouseId() 
    {
        return mouseId;
    }

    public void setMoveInDate(Date moveInDate) 
    {
        this.moveInDate = moveInDate;
    }

    public Date getMoveInDate() 
    {
        return moveInDate;
    }

    public void setMoveOutDate(Date moveOutDate) 
    {
        this.moveOutDate = moveOutDate;
    }

    public Date getMoveOutDate() 
    {
        return moveOutDate;
    }

    public void setIsCurrent(String isCurrent) 
    {
        this.isCurrent = isCurrent;
    }

    public String getIsCurrent() 
    {
        return isCurrent;
    }

    public void setMoveReason(String moveReason) 
    {
        this.moveReason = moveReason;
    }

    public String getMoveReason() 
    {
        return moveReason;
    }

    public void setPositionInCage(String positionInCage) 
    {
        this.positionInCage = positionInCage;
    }

    public String getPositionInCage() 
    {
        return positionInCage;
    }

    public void setPriorityLevel(Integer priorityLevel) 
    {
        this.priorityLevel = priorityLevel;
    }

    public Integer getPriorityLevel() 
    {
        return priorityLevel;
    }

    public void setSocialGroup(String socialGroup) 
    {
        this.socialGroup = socialGroup;
    }

    public String getSocialGroup() 
    {
        return socialGroup;
    }

    public void setIsolationFlag(String isolationFlag) 
    {
        this.isolationFlag = isolationFlag;
    }

    public String getIsolationFlag() 
    {
        return isolationFlag;
    }

    public void setFeedingSchedule(String feedingSchedule) 
    {
        this.feedingSchedule = feedingSchedule;
    }

    public String getFeedingSchedule() 
    {
        return feedingSchedule;
    }

    public void setMonitoringLevel(String monitoringLevel) 
    {
        this.monitoringLevel = monitoringLevel;
    }

    public String getMonitoringLevel() 
    {
        return monitoringLevel;
    }

    public void setEnvironmentalEnrichment(String environmentalEnrichment) 
    {
        this.environmentalEnrichment = environmentalEnrichment;
    }

    public String getEnvironmentalEnrichment() 
    {
        return environmentalEnrichment;
    }

    public void setHealthCheckFrequency(Long healthCheckFrequency) 
    {
        this.healthCheckFrequency = healthCheckFrequency;
    }

    public Long getHealthCheckFrequency() 
    {
        return healthCheckFrequency;
    }

    public void setLastHealthCheck(Date lastHealthCheck) 
    {
        this.lastHealthCheck = lastHealthCheck;
    }

    public Date getLastHealthCheck() 
    {
        return lastHealthCheck;
    }

    public void setNextHealthCheck(Date nextHealthCheck) 
    {
        this.nextHealthCheck = nextHealthCheck;
    }

    public Date getNextHealthCheck() 
    {
        return nextHealthCheck;
    }

    public void setWeightAtMoveIn(BigDecimal weightAtMoveIn) 
    {
        this.weightAtMoveIn = weightAtMoveIn;
    }

    public BigDecimal getWeightAtMoveIn() 
    {
        return weightAtMoveIn;
    }

    public void setWeightAtMoveOut(BigDecimal weightAtMoveOut) 
    {
        this.weightAtMoveOut = weightAtMoveOut;
    }

    public BigDecimal getWeightAtMoveOut() 
    {
        return weightAtMoveOut;
    }

    public void setBehavioralNotes(String behavioralNotes) 
    {
        this.behavioralNotes = behavioralNotes;
    }

    public String getBehavioralNotes() 
    {
        return behavioralNotes;
    }

    public void setSpecialCareInstructions(String specialCareInstructions) 
    {
        this.specialCareInstructions = specialCareInstructions;
    }

    public String getSpecialCareInstructions() 
    {
        return specialCareInstructions;
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

    public void setExtendData(String extendData) 
    {
        this.extendData = extendData;
    }

    public String getExtendData() 
    {
        return extendData;
    }

    public void setExtendInfo1(String extendInfo1) 
    {
        this.extendInfo1 = extendInfo1;
    }

    public String getExtendInfo1() 
    {
        return extendInfo1;
    }

    public void setExtendInfo2(String extendInfo2) 
    {
        this.extendInfo2 = extendInfo2;
    }

    public String getExtendInfo2() 
    {
        return extendInfo2;
    }

    public void setExtendInfo3(String extendInfo3) 
    {
        this.extendInfo3 = extendInfo3;
    }

    public String getExtendInfo3() 
    {
        return extendInfo3;
    }

    public void setExtendInfo4(String extendInfo4) 
    {
        this.extendInfo4 = extendInfo4;
    }

    public String getExtendInfo4() 
    {
        return extendInfo4;
    }

    public void setExtendInfo5(String extendInfo5) 
    {
        this.extendInfo5 = extendInfo5;
    }

    public String getExtendInfo5() 
    {
        return extendInfo5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cageMouseId", getCageMouseId())
            .append("cageId", getCageId())
            .append("mouseId", getMouseId())
            .append("moveInDate", getMoveInDate())
            .append("moveOutDate", getMoveOutDate())
            .append("isCurrent", getIsCurrent())
            .append("moveReason", getMoveReason())
            .append("positionInCage", getPositionInCage())
            .append("priorityLevel", getPriorityLevel())
            .append("socialGroup", getSocialGroup())
            .append("isolationFlag", getIsolationFlag())
            .append("feedingSchedule", getFeedingSchedule())
            .append("monitoringLevel", getMonitoringLevel())
            .append("environmentalEnrichment", getEnvironmentalEnrichment())
            .append("healthCheckFrequency", getHealthCheckFrequency())
            .append("lastHealthCheck", getLastHealthCheck())
            .append("nextHealthCheck", getNextHealthCheck())
            .append("weightAtMoveIn", getWeightAtMoveIn())
            .append("weightAtMoveOut", getWeightAtMoveOut())
            .append("behavioralNotes", getBehavioralNotes())
            .append("specialCareInstructions", getSpecialCareInstructions())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("extendInfo", getExtendInfo())
            .append("extendConfig", getExtendConfig())
            .append("extendData", getExtendData())
            .append("extendInfo1", getExtendInfo1())
            .append("extendInfo2", getExtendInfo2())
            .append("extendInfo3", getExtendInfo3())
            .append("extendInfo4", getExtendInfo4())
            .append("extendInfo5", getExtendInfo5())
            .toString();
    }
}

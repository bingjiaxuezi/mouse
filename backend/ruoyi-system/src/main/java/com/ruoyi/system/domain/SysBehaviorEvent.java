package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小鼠行为事件核心（记录具体行为事件）对象 sys_behavior_event
 * 
 * @author lczj
 * @date 2025-09-27
 */
public class SysBehaviorEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 事件ID */
    private Long eventId;

    /** 会话ID（外键关联sys_behavior_session.session_id） */
    @Excel(name = "会话ID", readConverterExp = "外=键关联sys_behavior_session.session_id")
    private Long sessionId;

    /** 小鼠ID（外键关联sys_mouse.mouse_id） */
    @Excel(name = "小鼠ID", readConverterExp = "外=键关联sys_mouse.mouse_id")
    private Long mouseId;

    /** 行为类型ID（外键关联sys_behavior_type.behavior_type_id） */
    @Excel(name = "行为类型ID", readConverterExp = "外=键关联sys_behavior_type.behavior_type_id")
    private Long behaviorTypeId;

    /** 事件开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "事件开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eventStartTime;

    /** 事件结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "事件结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eventEndTime;

    /** 持续时间（秒） */
    @Excel(name = "持续时间", readConverterExp = "秒=")
    private Long durationSeconds;

    /** 强度级别 */
    @Excel(name = "强度级别")
    private String intensityLevel;

    /** 检测置信度 */
    @Excel(name = "检测置信度")
    private BigDecimal confidenceScore;

    /** 检测方法 */
    @Excel(name = "检测方法")
    private String detectionMethod;

    /** 起始X坐标 */
    @Excel(name = "起始X坐标")
    private BigDecimal startXCoordinate;

    /** 起始Y坐标 */
    @Excel(name = "起始Y坐标")
    private BigDecimal startYCoordinate;

    /** 结束X坐标 */
    @Excel(name = "结束X坐标")
    private BigDecimal endXCoordinate;

    /** 结束Y坐标 */
    @Excel(name = "结束Y坐标")
    private BigDecimal endYCoordinate;

    /** 最大速度 */
    @Excel(name = "最大速度")
    private BigDecimal maxVelocity;

    /** 平均速度 */
    @Excel(name = "平均速度")
    private BigDecimal avgVelocity;

    /** 最大加速度 */
    @Excel(name = "最大加速度")
    private BigDecimal maxAcceleration;

    /** 移动距离 */
    @Excel(name = "移动距离")
    private BigDecimal distanceTraveled;

    /** 交互目标小鼠ID */
    @Excel(name = "交互目标小鼠ID")
    private Long interactionTargetMouseId;

    /** 交互类型 */
    @Excel(name = "交互类型")
    private String interactionType;

    /** 异常标识 */
    @Excel(name = "异常标识")
    private String anomalyFlag;

    /** 数据质量评分 */
    @Excel(name = "数据质量评分")
    private BigDecimal qualityScore;

    /** 验证状态 */
    @Excel(name = "验证状态")
    private String validationStatus;

    /** 验证者ID */
    @Excel(name = "验证者ID")
    private String validatorId;

    /** 验证时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "验证时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validationTime;

    /** 扩展信息(JSON格式) */
    private String extendInfo;

    /** 扩展配置(JSON格式) */
    private String extendConfig;

    /** 扩展数据(JSON格式) */
    private String extendData;

    /** 扩展信息1 */
    private String extendInfo1;

    /** 扩展信息2 */
    private String extendInfo2;

    /** 扩展信息3(JSON格式) */
    private String extendInfo3;

    /** 扩展信息4(数值型) */
    private Long extendInfo4;

    /** 扩展信息5(小数型) */
    private BigDecimal extendInfo5;

    /** 创建者 */
    @Excel(name = "创建者")
    private String buildBy;

    /** 创建时间 */
    private Date buildTime;

    /** 更新者 */
    private String modifyBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** 删除标志 */
    private String delFlag;

    /** 小鼠行为监测会话信息 */
    private List<SysBehaviorSession> sysBehaviorSessionList;

    public void setEventId(Long eventId) 
    {
        this.eventId = eventId;
    }

    public Long getEventId() 
    {
        return eventId;
    }

    public void setSessionId(Long sessionId) 
    {
        this.sessionId = sessionId;
    }

    public Long getSessionId() 
    {
        return sessionId;
    }

    public void setMouseId(Long mouseId) 
    {
        this.mouseId = mouseId;
    }

    public Long getMouseId() 
    {
        return mouseId;
    }

    public void setBehaviorTypeId(Long behaviorTypeId) 
    {
        this.behaviorTypeId = behaviorTypeId;
    }

    public Long getBehaviorTypeId() 
    {
        return behaviorTypeId;
    }

    public void setEventStartTime(Date eventStartTime) 
    {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventStartTime() 
    {
        return eventStartTime;
    }

    public void setEventEndTime(Date eventEndTime) 
    {
        this.eventEndTime = eventEndTime;
    }

    public Date getEventEndTime() 
    {
        return eventEndTime;
    }

    public void setDurationSeconds(Long durationSeconds) 
    {
        this.durationSeconds = durationSeconds;
    }

    public Long getDurationSeconds() 
    {
        return durationSeconds;
    }

    public void setIntensityLevel(String intensityLevel) 
    {
        this.intensityLevel = intensityLevel;
    }

    public String getIntensityLevel() 
    {
        return intensityLevel;
    }

    public void setConfidenceScore(BigDecimal confidenceScore) 
    {
        this.confidenceScore = confidenceScore;
    }

    public BigDecimal getConfidenceScore() 
    {
        return confidenceScore;
    }

    public void setDetectionMethod(String detectionMethod) 
    {
        this.detectionMethod = detectionMethod;
    }

    public String getDetectionMethod() 
    {
        return detectionMethod;
    }

    public void setStartXCoordinate(BigDecimal startXCoordinate) 
    {
        this.startXCoordinate = startXCoordinate;
    }

    public BigDecimal getStartXCoordinate() 
    {
        return startXCoordinate;
    }

    public void setStartYCoordinate(BigDecimal startYCoordinate) 
    {
        this.startYCoordinate = startYCoordinate;
    }

    public BigDecimal getStartYCoordinate() 
    {
        return startYCoordinate;
    }

    public void setEndXCoordinate(BigDecimal endXCoordinate) 
    {
        this.endXCoordinate = endXCoordinate;
    }

    public BigDecimal getEndXCoordinate() 
    {
        return endXCoordinate;
    }

    public void setEndYCoordinate(BigDecimal endYCoordinate) 
    {
        this.endYCoordinate = endYCoordinate;
    }

    public BigDecimal getEndYCoordinate() 
    {
        return endYCoordinate;
    }

    public void setMaxVelocity(BigDecimal maxVelocity) 
    {
        this.maxVelocity = maxVelocity;
    }

    public BigDecimal getMaxVelocity() 
    {
        return maxVelocity;
    }

    public void setAvgVelocity(BigDecimal avgVelocity) 
    {
        this.avgVelocity = avgVelocity;
    }

    public BigDecimal getAvgVelocity() 
    {
        return avgVelocity;
    }

    public void setMaxAcceleration(BigDecimal maxAcceleration) 
    {
        this.maxAcceleration = maxAcceleration;
    }

    public BigDecimal getMaxAcceleration() 
    {
        return maxAcceleration;
    }

    public void setDistanceTraveled(BigDecimal distanceTraveled) 
    {
        this.distanceTraveled = distanceTraveled;
    }

    public BigDecimal getDistanceTraveled() 
    {
        return distanceTraveled;
    }

    public void setInteractionTargetMouseId(Long interactionTargetMouseId) 
    {
        this.interactionTargetMouseId = interactionTargetMouseId;
    }

    public Long getInteractionTargetMouseId() 
    {
        return interactionTargetMouseId;
    }

    public void setInteractionType(String interactionType) 
    {
        this.interactionType = interactionType;
    }

    public String getInteractionType() 
    {
        return interactionType;
    }

    public void setAnomalyFlag(String anomalyFlag) 
    {
        this.anomalyFlag = anomalyFlag;
    }

    public String getAnomalyFlag() 
    {
        return anomalyFlag;
    }

    public void setQualityScore(BigDecimal qualityScore) 
    {
        this.qualityScore = qualityScore;
    }

    public BigDecimal getQualityScore() 
    {
        return qualityScore;
    }

    public void setValidationStatus(String validationStatus) 
    {
        this.validationStatus = validationStatus;
    }

    public String getValidationStatus() 
    {
        return validationStatus;
    }

    public void setValidatorId(String validatorId) 
    {
        this.validatorId = validatorId;
    }

    public String getValidatorId() 
    {
        return validatorId;
    }

    public void setValidationTime(Date validationTime) 
    {
        this.validationTime = validationTime;
    }

    public Date getValidationTime() 
    {
        return validationTime;
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

    public void setExtendInfo4(Long extendInfo4) 
    {
        this.extendInfo4 = extendInfo4;
    }

    public Long getExtendInfo4() 
    {
        return extendInfo4;
    }

    public void setExtendInfo5(BigDecimal extendInfo5) 
    {
        this.extendInfo5 = extendInfo5;
    }

    public BigDecimal getExtendInfo5() 
    {
        return extendInfo5;
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

    public List<SysBehaviorSession> getSysBehaviorSessionList()
    {
        return sysBehaviorSessionList;
    }

    public void setSysBehaviorSessionList(List<SysBehaviorSession> sysBehaviorSessionList)
    {
        this.sysBehaviorSessionList = sysBehaviorSessionList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eventId", getEventId())
            .append("sessionId", getSessionId())
            .append("mouseId", getMouseId())
            .append("behaviorTypeId", getBehaviorTypeId())
            .append("eventStartTime", getEventStartTime())
            .append("eventEndTime", getEventEndTime())
            .append("durationSeconds", getDurationSeconds())
            .append("intensityLevel", getIntensityLevel())
            .append("confidenceScore", getConfidenceScore())
            .append("detectionMethod", getDetectionMethod())
            .append("startXCoordinate", getStartXCoordinate())
            .append("startYCoordinate", getStartYCoordinate())
            .append("endXCoordinate", getEndXCoordinate())
            .append("endYCoordinate", getEndYCoordinate())
            .append("maxVelocity", getMaxVelocity())
            .append("avgVelocity", getAvgVelocity())
            .append("maxAcceleration", getMaxAcceleration())
            .append("distanceTraveled", getDistanceTraveled())
            .append("interactionTargetMouseId", getInteractionTargetMouseId())
            .append("interactionType", getInteractionType())
            .append("anomalyFlag", getAnomalyFlag())
            .append("qualityScore", getQualityScore())
            .append("validationStatus", getValidationStatus())
            .append("validatorId", getValidatorId())
            .append("validationTime", getValidationTime())
            .append("extendInfo", getExtendInfo())
            .append("extendConfig", getExtendConfig())
            .append("extendData", getExtendData())
            .append("extendInfo1", getExtendInfo1())
            .append("extendInfo2", getExtendInfo2())
            .append("extendInfo3", getExtendInfo3())
            .append("extendInfo4", getExtendInfo4())
            .append("extendInfo5", getExtendInfo5())
            .append("buildBy", getBuildBy())
            .append("buildTime", getBuildTime())
            .append("modifyBy", getModifyBy())
            .append("modifyTime", getModifyTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("sysBehaviorSessionList", getSysBehaviorSessionList())
            .toString();
    }
}

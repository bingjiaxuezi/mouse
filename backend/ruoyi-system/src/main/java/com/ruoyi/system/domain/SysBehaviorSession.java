package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小鼠行为监测会话对象 sys_behavior_session
 * 
 * @author lczj
 * @date 2025-09-21
 */
public class SysBehaviorSession extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会话ID */
    private Long sessionId;

    /** 会话编号 */
    @Excel(name = "会话编号")
    private String sessionCode;

    /** 关联实验ID */
    @Excel(name = "关联实验ID")
    private Long experimentId;

    /** 笼子ID */
    @Excel(name = "笼子ID")
    private Long cageId;

    /** 行为会话开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "行为会话开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sessionStartTime;

    /** 行为会话结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "行为会话结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sessionEndTime;

    /** 计划持续时间（分钟） */
    @Excel(name = "计划持续时间", readConverterExp = "分=钟")
    private Long plannedDuration;

    /** 实际持续时间（分钟） */
    @Excel(name = "实际持续时间", readConverterExp = "分=钟")
    private Long actualDuration;

    /** 行为会话状态 */
    @Excel(name = "行为会话状态")
    private String sessionStatus;

    /** 会话类型 */
    @Excel(name = "会话类型")
    private String sessionType;

    /** 监测配置（JSON格式） */
    @Excel(name = "监测配置", readConverterExp = "J=SON格式")
    private String monitoringConfig;

    /** 平均温度（°C） */
    @Excel(name = "平均温度", readConverterExp = "°=C")
    private BigDecimal avgTemperature;

    /** 平均湿度（%） */
    @Excel(name = "平均湿度", readConverterExp = "%=")
    private BigDecimal avgHumidity;

    /** 平均光照强度 */
    @Excel(name = "平均光照强度")
    private BigDecimal avgLightIntensity;

    /** 平均声音级别 */
    @Excel(name = "平均声音级别")
    private BigDecimal avgSoundLevel;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String deviceId;

    /** 设备版本 */
    @Excel(name = "设备版本")
    private String deviceVersion;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private String operatorId;

    /** 总事件数 */
    @Excel(name = "总事件数")
    private Long totalEvents;

    /** 参与小鼠数 */
    @Excel(name = "参与小鼠数")
    private Long totalMice;

    /** 数据质量评分 */
    @Excel(name = "数据质量评分")
    private BigDecimal dataQualityScore;

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

    public void setSessionId(Long sessionId) 
    {
        this.sessionId = sessionId;
    }

    public Long getSessionId() 
    {
        return sessionId;
    }

    public void setSessionCode(String sessionCode) 
    {
        this.sessionCode = sessionCode;
    }

    public String getSessionCode() 
    {
        return sessionCode;
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

    public void setSessionStartTime(Date sessionStartTime) 
    {
        this.sessionStartTime = sessionStartTime;
    }

    public Date getSessionStartTime() 
    {
        return sessionStartTime;
    }

    public void setSessionEndTime(Date sessionEndTime) 
    {
        this.sessionEndTime = sessionEndTime;
    }

    public Date getSessionEndTime() 
    {
        return sessionEndTime;
    }

    public void setPlannedDuration(Long plannedDuration) 
    {
        this.plannedDuration = plannedDuration;
    }

    public Long getPlannedDuration() 
    {
        return plannedDuration;
    }

    public void setActualDuration(Long actualDuration) 
    {
        this.actualDuration = actualDuration;
    }

    public Long getActualDuration() 
    {
        return actualDuration;
    }

    public void setSessionStatus(String sessionStatus) 
    {
        this.sessionStatus = sessionStatus;
    }

    public String getSessionStatus() 
    {
        return sessionStatus;
    }

    public void setSessionType(String sessionType) 
    {
        this.sessionType = sessionType;
    }

    public String getSessionType() 
    {
        return sessionType;
    }

    public void setMonitoringConfig(String monitoringConfig) 
    {
        this.monitoringConfig = monitoringConfig;
    }

    public String getMonitoringConfig() 
    {
        return monitoringConfig;
    }

    public void setAvgTemperature(BigDecimal avgTemperature) 
    {
        this.avgTemperature = avgTemperature;
    }

    public BigDecimal getAvgTemperature() 
    {
        return avgTemperature;
    }

    public void setAvgHumidity(BigDecimal avgHumidity) 
    {
        this.avgHumidity = avgHumidity;
    }

    public BigDecimal getAvgHumidity() 
    {
        return avgHumidity;
    }

    public void setAvgLightIntensity(BigDecimal avgLightIntensity) 
    {
        this.avgLightIntensity = avgLightIntensity;
    }

    public BigDecimal getAvgLightIntensity() 
    {
        return avgLightIntensity;
    }

    public void setAvgSoundLevel(BigDecimal avgSoundLevel) 
    {
        this.avgSoundLevel = avgSoundLevel;
    }

    public BigDecimal getAvgSoundLevel() 
    {
        return avgSoundLevel;
    }

    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }

    public void setDeviceVersion(String deviceVersion) 
    {
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceVersion() 
    {
        return deviceVersion;
    }

    public void setOperatorId(String operatorId) 
    {
        this.operatorId = operatorId;
    }

    public String getOperatorId() 
    {
        return operatorId;
    }

    public void setTotalEvents(Long totalEvents) 
    {
        this.totalEvents = totalEvents;
    }

    public Long getTotalEvents() 
    {
        return totalEvents;
    }

    public void setTotalMice(Long totalMice) 
    {
        this.totalMice = totalMice;
    }

    public Long getTotalMice() 
    {
        return totalMice;
    }

    public void setDataQualityScore(BigDecimal dataQualityScore) 
    {
        this.dataQualityScore = dataQualityScore;
    }

    public BigDecimal getDataQualityScore() 
    {
        return dataQualityScore;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sessionId", getSessionId())
            .append("sessionCode", getSessionCode())
            .append("experimentId", getExperimentId())
            .append("cageId", getCageId())
            .append("sessionStartTime", getSessionStartTime())
            .append("sessionEndTime", getSessionEndTime())
            .append("plannedDuration", getPlannedDuration())
            .append("actualDuration", getActualDuration())
            .append("sessionStatus", getSessionStatus())
            .append("sessionType", getSessionType())
            .append("monitoringConfig", getMonitoringConfig())
            .append("avgTemperature", getAvgTemperature())
            .append("avgHumidity", getAvgHumidity())
            .append("avgLightIntensity", getAvgLightIntensity())
            .append("avgSoundLevel", getAvgSoundLevel())
            .append("deviceId", getDeviceId())
            .append("deviceVersion", getDeviceVersion())
            .append("operatorId", getOperatorId())
            .append("totalEvents", getTotalEvents())
            .append("totalMice", getTotalMice())
            .append("dataQualityScore", getDataQualityScore())
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
            .toString();
    }
}

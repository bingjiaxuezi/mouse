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
 * 行为事件环境数据对象 sys_behavior_environment
 * 
 * @author lczj
 * @date 2025-10-07
 */
public class SysBehaviorEnvironment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 环境数据ID */
    private Long envId;

    /** 事件ID */
    @Excel(name = "事件ID")
    private Long eventId;

    /** 温度（°C） */
    @Excel(name = "温度", readConverterExp = "°=C")
    private BigDecimal temperature;

    /** 湿度（%） */
    @Excel(name = "湿度", readConverterExp = "%=")
    private BigDecimal humidity;

    /** 光照强度 */
    @Excel(name = "光照强度")
    private BigDecimal lightIntensity;

    /** 声音级别 */
    @Excel(name = "声音级别")
    private BigDecimal soundLevel;

    /** 气压 */
    @Excel(name = "气压")
    private BigDecimal airPressure;

    /** CO2浓度 */
    @Excel(name = "CO2浓度")
    private BigDecimal co2Level;

    /** 光照周期阶段 */
    @Excel(name = "光照周期阶段")
    private String lightCyclePhase;

    /** 光照色温（K） */
    @Excel(name = "光照色温", readConverterExp = "K=")
    private Long lightColorTemp;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

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
    private Date modifyTime;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 小鼠行为事件核心（记录具体行为事件）信息 */
    private List<SysBehaviorEvent> sysBehaviorEventList;

    public void setEnvId(Long envId) 
    {
        this.envId = envId;
    }

    public Long getEnvId() 
    {
        return envId;
    }

    public void setEventId(Long eventId) 
    {
        this.eventId = eventId;
    }

    public Long getEventId() 
    {
        return eventId;
    }

    public void setTemperature(BigDecimal temperature) 
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature() 
    {
        return temperature;
    }

    public void setHumidity(BigDecimal humidity) 
    {
        this.humidity = humidity;
    }

    public BigDecimal getHumidity() 
    {
        return humidity;
    }

    public void setLightIntensity(BigDecimal lightIntensity) 
    {
        this.lightIntensity = lightIntensity;
    }

    public BigDecimal getLightIntensity() 
    {
        return lightIntensity;
    }

    public void setSoundLevel(BigDecimal soundLevel) 
    {
        this.soundLevel = soundLevel;
    }

    public BigDecimal getSoundLevel() 
    {
        return soundLevel;
    }

    public void setAirPressure(BigDecimal airPressure) 
    {
        this.airPressure = airPressure;
    }

    public BigDecimal getAirPressure() 
    {
        return airPressure;
    }

    public void setCo2Level(BigDecimal co2Level) 
    {
        this.co2Level = co2Level;
    }

    public BigDecimal getCo2Level() 
    {
        return co2Level;
    }

    public void setLightCyclePhase(String lightCyclePhase) 
    {
        this.lightCyclePhase = lightCyclePhase;
    }

    public String getLightCyclePhase() 
    {
        return lightCyclePhase;
    }

    public void setLightColorTemp(Long lightColorTemp) 
    {
        this.lightColorTemp = lightColorTemp;
    }

    public Long getLightColorTemp() 
    {
        return lightColorTemp;
    }

    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
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

    public List<SysBehaviorEvent> getSysBehaviorEventList()
    {
        return sysBehaviorEventList;
    }

    public void setSysBehaviorEventList(List<SysBehaviorEvent> sysBehaviorEventList)
    {
        this.sysBehaviorEventList = sysBehaviorEventList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("envId", getEnvId())
            .append("eventId", getEventId())
            .append("temperature", getTemperature())
            .append("humidity", getHumidity())
            .append("lightIntensity", getLightIntensity())
            .append("soundLevel", getSoundLevel())
            .append("airPressure", getAirPressure())
            .append("co2Level", getCo2Level())
            .append("lightCyclePhase", getLightCyclePhase())
            .append("lightColorTemp", getLightColorTemp())
            .append("recordTime", getRecordTime())
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
            .append("sysBehaviorEventList", getSysBehaviorEventList())
            .toString();
    }
}

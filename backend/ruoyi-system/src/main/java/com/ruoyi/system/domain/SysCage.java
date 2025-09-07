package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 笼子管理对象 sys_cage
 * 
 * @author lczj
 * @date 2025-09-07
 */
public class SysCage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 笼子ID */
    private Long cageId;

    /** 笼子编号 */
    @Excel(name = "笼子编号")
    private String cageCode;

    /** 笼子名称 */
    @Excel(name = "笼子名称")
    private String cageName;

    /** 笼子类型 */
    @Excel(name = "笼子类型")
    private String cageType;

    /** 实验室房间 */
    @Excel(name = "实验室房间")
    private String laboratoryRoom;

    /** 货架编号 */
    @Excel(name = "货架编号")
    private String rackNumber;

    /** 行位置 */
    @Excel(name = "行位置")
    private Long positionRow;

    /** 列位置 */
    @Excel(name = "列位置")
    private Long positionColumn;

    /** 最大容量(只) */
    @Excel(name = "最大容量(只)")
    private Long maxCapacity;

    /** 当前小鼠数量 */
    @Excel(name = "当前小鼠数量")
    private Long currentCount;

    /** 笼子状态 */
    @Excel(name = "笼子状态")
    private String cageStatus;

    /** 温度范围(°C) */
    @Excel(name = "温度范围(°C)")
    private String temperatureRange;

    /** 湿度范围(%) */
    @Excel(name = "湿度范围(%)")
    private String humidityRange;

    /** 笼子二维码内容 */
    private String qrCodeContent;

    /** 二维码图片URL */
    @Excel(name = "二维码图片URL")
    private String qrCodeImageUrl;

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

    /** 创建者 */
    private String buildBy;

    /** 创建时间 */
    private Date buildTime;

    /** 更新者 */
    private String modifyBy;

    /** 创建时间 */
    private Date modifyTime;

    /** 使用标志 */
    private String delFlag;

    public void setCageId(Long cageId) 
    {
        this.cageId = cageId;
    }

    public Long getCageId() 
    {
        return cageId;
    }

    public void setCageCode(String cageCode) 
    {
        this.cageCode = cageCode;
    }

    public String getCageCode() 
    {
        return cageCode;
    }

    public void setCageName(String cageName) 
    {
        this.cageName = cageName;
    }

    public String getCageName() 
    {
        return cageName;
    }

    public void setCageType(String cageType) 
    {
        this.cageType = cageType;
    }

    public String getCageType() 
    {
        return cageType;
    }

    public void setLaboratoryRoom(String laboratoryRoom) 
    {
        this.laboratoryRoom = laboratoryRoom;
    }

    public String getLaboratoryRoom() 
    {
        return laboratoryRoom;
    }

    public void setRackNumber(String rackNumber) 
    {
        this.rackNumber = rackNumber;
    }

    public String getRackNumber() 
    {
        return rackNumber;
    }

    public void setPositionRow(Long positionRow) 
    {
        this.positionRow = positionRow;
    }

    public Long getPositionRow() 
    {
        return positionRow;
    }

    public void setPositionColumn(Long positionColumn) 
    {
        this.positionColumn = positionColumn;
    }

    public Long getPositionColumn() 
    {
        return positionColumn;
    }

    public void setMaxCapacity(Long maxCapacity) 
    {
        this.maxCapacity = maxCapacity;
    }

    public Long getMaxCapacity() 
    {
        return maxCapacity;
    }

    public void setCurrentCount(Long currentCount) 
    {
        this.currentCount = currentCount;
    }

    public Long getCurrentCount() 
    {
        return currentCount;
    }

    public void setCageStatus(String cageStatus) 
    {
        this.cageStatus = cageStatus;
    }

    public String getCageStatus() 
    {
        return cageStatus;
    }

    public void setTemperatureRange(String temperatureRange) 
    {
        this.temperatureRange = temperatureRange;
    }

    public String getTemperatureRange() 
    {
        return temperatureRange;
    }

    public void setHumidityRange(String humidityRange) 
    {
        this.humidityRange = humidityRange;
    }

    public String getHumidityRange() 
    {
        return humidityRange;
    }

    public void setQrCodeContent(String qrCodeContent) 
    {
        this.qrCodeContent = qrCodeContent;
    }

    public String getQrCodeContent() 
    {
        return qrCodeContent;
    }

    public void setQrCodeImageUrl(String qrCodeImageUrl) 
    {
        this.qrCodeImageUrl = qrCodeImageUrl;
    }

    public String getQrCodeImageUrl() 
    {
        return qrCodeImageUrl;
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
            .append("cageId", getCageId())
            .append("cageCode", getCageCode())
            .append("cageName", getCageName())
            .append("cageType", getCageType())
            .append("laboratoryRoom", getLaboratoryRoom())
            .append("rackNumber", getRackNumber())
            .append("positionRow", getPositionRow())
            .append("positionColumn", getPositionColumn())
            .append("maxCapacity", getMaxCapacity())
            .append("currentCount", getCurrentCount())
            .append("cageStatus", getCageStatus())
            .append("temperatureRange", getTemperatureRange())
            .append("humidityRange", getHumidityRange())
            .append("qrCodeContent", getQrCodeContent())
            .append("qrCodeImageUrl", getQrCodeImageUrl())
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

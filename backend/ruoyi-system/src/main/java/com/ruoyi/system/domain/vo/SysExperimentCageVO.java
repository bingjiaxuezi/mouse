package com.ruoyi.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验笼子关系视图对象 sys_experiment_cage
 * 
 * @author ruoyi
 * @date 2025-09-07
 */
public class SysExperimentCageVO extends BaseEntity
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

    /** 笼子编号 */
    @Excel(name = "笼子编号")
    private String cageCode;

    /** 笼子名称 */
    @Excel(name = "笼子名称")
    private String cageName;

    /** 笼子类型 */
    @Excel(name = "笼子类型")
    private String cageType;

    /** 笼子状态 */
    @Excel(name = "笼子状态")
    private String cageStatus;

    /** 实验室房间 */
    @Excel(name = "实验室房间")
    private String laboratoryRoom;

    /** 最大容量(只) */
    @Excel(name = "最大容量(只)")
    private Long maxCapacity;

    /** 当前小鼠数量 */
    @Excel(name = "当前小鼠数量")
    private Long currentCount;

    /** 绑定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bindTime;

    /** 解绑时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "解绑时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date unbindTime;

    /** 绑定状态 */
    @Excel(name = "绑定状态")
    private String bindStatus;

    /** 绑定方式 */
    @Excel(name = "绑定方式")
    private String bindMethod;

    /** 绑定操作员 */
    @Excel(name = "绑定操作员")
    private String bindOperator;

    /** 笼子角色 */
    @Excel(name = "笼子角色")
    private String cageRole;

    /** 监控配置(JSON格式) */
    @Excel(name = "监控配置(JSON格式)")
    private String monitoringConfig;

    /** 创建者 */
    @Excel(name = "创建者")
    private String buildBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date buildTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /** 修改者 */
    @Excel(name = "修改者")
    private String modifyBy;

    // Getters and Setters
    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    public Long getCageId() {
        return cageId;
    }

    public void setCageId(Long cageId) {
        this.cageId = cageId;
    }

    public String getCageCode() {
        return cageCode;
    }

    public void setCageCode(String cageCode) {
        this.cageCode = cageCode;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;
    }

    public String getCageType() {
        return cageType;
    }

    public void setCageType(String cageType) {
        this.cageType = cageType;
    }

    public String getCageStatus() {
        return cageStatus;
    }

    public void setCageStatus(String cageStatus) {
        this.cageStatus = cageStatus;
    }

    public String getLaboratoryRoom() {
        return laboratoryRoom;
    }

    public void setLaboratoryRoom(String laboratoryRoom) {
        this.laboratoryRoom = laboratoryRoom;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getBindMethod() {
        return bindMethod;
    }

    public void setBindMethod(String bindMethod) {
        this.bindMethod = bindMethod;
    }

    public String getBindOperator() {
        return bindOperator;
    }

    public void setBindOperator(String bindOperator) {
        this.bindOperator = bindOperator;
    }

    public String getCageRole() {
        return cageRole;
    }

    public void setCageRole(String cageRole) {
        this.cageRole = cageRole;
    }

    public String getMonitoringConfig() {
        return monitoringConfig;
    }

    public void setMonitoringConfig(String monitoringConfig) {
        this.monitoringConfig = monitoringConfig;
    }

    public String getBuildBy() {
        return buildBy;
    }

    public void setBuildBy(String buildBy) {
        this.buildBy = buildBy;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
}
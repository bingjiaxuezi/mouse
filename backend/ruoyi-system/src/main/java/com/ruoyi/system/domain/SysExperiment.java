package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验详情展示对象 sys_experiment
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
public class SysExperiment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 实验ID */
    private Long experimentId;

    /** 实验编号 */
    @Excel(name = "实验编号")
    private String experimentCode;

    /** 实验名称 */
    @Excel(name = "实验名称")
    private String experimentName;

    /** 实验类型 */
    @Excel(name = "实验类型")
    private String experimentType;

    /** 实验模板名称 */
    @Excel(name = "实验模板名称")
    private String experimentTemplate;

    /** 实验目标 */
    @Excel(name = "实验目标")
    private String experimentObjective;

    /** 实验描述 */
    @Excel(name = "实验描述")
    private String experimentDesc;

    /** 实验状态 */
    @Excel(name = "实验状态")
    private String experimentStatus;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedBeginTime;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedFinishTime;

    /** 实际开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualBeginTime;

    /** 实际结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualFinishTime;

    /** 实验周期(自动计算) */
    @Excel(name = "实验周期(自动计算)")
    private Long experimentDuration;

    /** 主要研究员ID */
    @Excel(name = "主要研究员ID")
    private Long principalResearcher;

    /** 协作研究员ID列表 */
    @Excel(name = "协作研究员ID列表")
    private String coResearchers;

    /** 实验团队名称 */
    @Excel(name = "实验团队名称")
    private String experimentTeam;

    /** 实验配置参数 */
    @Excel(name = "实验配置参数")
    private String experimentConfig;

    /** 实验二维码内容 */
    @Excel(name = "实验二维码内容")
    private String qrCodeContent;

    /** 二维码图片URL */
    @Excel(name = "二维码图片URL")
    private String qrCodeImageUrl;

    /** 关联笼子总数 */
    @Excel(name = "关联笼子总数")
    private Long totalCages;

    /** 关联小鼠总数 */
    @Excel(name = "关联小鼠总数")
    private Long totalMice;

    /** 创建者 */
    private String buildBy;

    /** 创建时间 */
    private Date buildTime;

    /** 更新者 */
    private String modifyBy;

    /** 删除标志 */
    private String delFlag;

    /** 创建时间 */
    private Date modifyTime;

    public void setExperimentId(Long experimentId) 
    {
        this.experimentId = experimentId;
    }

    public Long getExperimentId() 
    {
        return experimentId;
    }

    public void setExperimentCode(String experimentCode) 
    {
        this.experimentCode = experimentCode;
    }

    public String getExperimentCode() 
    {
        return experimentCode;
    }

    public void setExperimentName(String experimentName) 
    {
        this.experimentName = experimentName;
    }

    public String getExperimentName() 
    {
        return experimentName;
    }

    public void setExperimentType(String experimentType) 
    {
        this.experimentType = experimentType;
    }

    public String getExperimentType() 
    {
        return experimentType;
    }

    public void setExperimentTemplate(String experimentTemplate) 
    {
        this.experimentTemplate = experimentTemplate;
    }

    public String getExperimentTemplate() 
    {
        return experimentTemplate;
    }

    public void setExperimentObjective(String experimentObjective) 
    {
        this.experimentObjective = experimentObjective;
    }

    public String getExperimentObjective() 
    {
        return experimentObjective;
    }

    public void setExperimentDesc(String experimentDesc) 
    {
        this.experimentDesc = experimentDesc;
    }

    public String getExperimentDesc() 
    {
        return experimentDesc;
    }

    public void setExperimentStatus(String experimentStatus) 
    {
        this.experimentStatus = experimentStatus;
    }

    public String getExperimentStatus() 
    {
        return experimentStatus;
    }

    public void setPlannedBeginTime(Date plannedBeginTime) 
    {
        this.plannedBeginTime = plannedBeginTime;
    }

    public Date getPlannedBeginTime() 
    {
        return plannedBeginTime;
    }

    public void setPlannedFinishTime(Date plannedFinishTime) 
    {
        this.plannedFinishTime = plannedFinishTime;
    }

    public Date getPlannedFinishTime() 
    {
        return plannedFinishTime;
    }

    public void setActualBeginTime(Date actualBeginTime) 
    {
        this.actualBeginTime = actualBeginTime;
    }

    public Date getActualBeginTime() 
    {
        return actualBeginTime;
    }

    public void setActualFinishTime(Date actualFinishTime) 
    {
        this.actualFinishTime = actualFinishTime;
    }

    public Date getActualFinishTime() 
    {
        return actualFinishTime;
    }

    public void setExperimentDuration(Long experimentDuration) 
    {
        this.experimentDuration = experimentDuration;
    }

    public Long getExperimentDuration() 
    {
        return experimentDuration;
    }

    public void setPrincipalResearcher(Long principalResearcher) 
    {
        this.principalResearcher = principalResearcher;
    }

    public Long getPrincipalResearcher() 
    {
        return principalResearcher;
    }

    public void setCoResearchers(String coResearchers) 
    {
        this.coResearchers = coResearchers;
    }

    public String getCoResearchers() 
    {
        return coResearchers;
    }

    public void setExperimentTeam(String experimentTeam) 
    {
        this.experimentTeam = experimentTeam;
    }

    public String getExperimentTeam() 
    {
        return experimentTeam;
    }

    public void setExperimentConfig(String experimentConfig) 
    {
        this.experimentConfig = experimentConfig;
    }

    public String getExperimentConfig() 
    {
        return experimentConfig;
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

    public void setTotalCages(Long totalCages) 
    {
        this.totalCages = totalCages;
    }

    public Long getTotalCages() 
    {
        return totalCages;
    }

    public void setTotalMice(Long totalMice) 
    {
        this.totalMice = totalMice;
    }

    public Long getTotalMice() 
    {
        return totalMice;
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

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("experimentId", getExperimentId())
            .append("experimentCode", getExperimentCode())
            .append("experimentName", getExperimentName())
            .append("experimentType", getExperimentType())
            .append("experimentTemplate", getExperimentTemplate())
            .append("experimentObjective", getExperimentObjective())
            .append("experimentDesc", getExperimentDesc())
            .append("experimentStatus", getExperimentStatus())
            .append("plannedBeginTime", getPlannedBeginTime())
            .append("plannedFinishTime", getPlannedFinishTime())
            .append("actualBeginTime", getActualBeginTime())
            .append("actualFinishTime", getActualFinishTime())
            .append("experimentDuration", getExperimentDuration())
            .append("principalResearcher", getPrincipalResearcher())
            .append("coResearchers", getCoResearchers())
            .append("experimentTeam", getExperimentTeam())
            .append("experimentConfig", getExperimentConfig())
            .append("qrCodeContent", getQrCodeContent())
            .append("qrCodeImageUrl", getQrCodeImageUrl())
            .append("totalCages", getTotalCages())
            .append("totalMice", getTotalMice())
            .append("buildBy", getBuildBy())
            .append("buildTime", getBuildTime())
            .append("modifyBy", getModifyBy())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}

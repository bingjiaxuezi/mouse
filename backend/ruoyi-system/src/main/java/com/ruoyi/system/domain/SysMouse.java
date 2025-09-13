package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小鼠基础信息对象 sys_mouse
 * 
 * @author ruoyi
 * @date 2025-09-13
 */
public class SysMouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 小鼠ID */
    private Long mouseId;

    /** 小鼠编号 */
    @Excel(name = "小鼠编号")
    private String mouseCode;

    /** 小鼠名称（不填后端自动生成） */
    @Excel(name = "小鼠名称", readConverterExp = "不=填后端自动生成")
    private String mouseName;

    /** 物种 */
    @Excel(name = "物种")
    private String species;

    /** 品系 */
    @Excel(name = "品系")
    private String strain;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 体重（克） */
    @Excel(name = "体重", readConverterExp = "克=")
    private BigDecimal weight;

    /** 健康状态 */
    @Excel(name = "健康状态")
    private String healthStatus;

    /** 照片 */
    @Excel(name = "照片")
    private String photoUrl;

    /** RFID标签号 */
    @Excel(name = "RFID标签号")
    private String rfidTag;

    /** 耳标号 */
    @Excel(name = "耳标号")
    private String earTag;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 到达日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "到达日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrivalDate;

    /** 断奶日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "断奶日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date weaningDate;

    /** 基因型信息 */
    @Excel(name = "基因型信息")
    private String genotype;

    /** 繁殖状态 */
    @Excel(name = "繁殖状态")
    private String breedingStatus;

    /** 父亲小鼠ID */
    @Excel(name = "父亲小鼠ID")
    private Long fatherMouseId;

    /** 母亲小鼠ID */
    @Excel(name = "母亲小鼠ID")
    private Long motherMouseId;

    /** 删除标志 */
    private String delFlag;

    /** 批量生成数量 */
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

    public void setMouseId(Long mouseId) 
    {
        this.mouseId = mouseId;
    }

    public Long getMouseId() 
    {
        return mouseId;
    }

    public void setMouseCode(String mouseCode) 
    {
        this.mouseCode = mouseCode;
    }

    public String getMouseCode() 
    {
        return mouseCode;
    }

    public void setMouseName(String mouseName) 
    {
        this.mouseName = mouseName;
    }

    public String getMouseName() 
    {
        return mouseName;
    }

    public void setSpecies(String species) 
    {
        this.species = species;
    }

    public String getSpecies() 
    {
        return species;
    }

    public void setStrain(String strain) 
    {
        this.strain = strain;
    }

    public String getStrain() 
    {
        return strain;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setBirthDate(Date birthDate) 
    {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() 
    {
        return birthDate;
    }

    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }

    public void setHealthStatus(String healthStatus) 
    {
        this.healthStatus = healthStatus;
    }

    public String getHealthStatus() 
    {
        return healthStatus;
    }

    public void setPhotoUrl(String photoUrl) 
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() 
    {
        return photoUrl;
    }

    public void setRfidTag(String rfidTag) 
    {
        this.rfidTag = rfidTag;
    }

    public String getRfidTag() 
    {
        return rfidTag;
    }

    public void setEarTag(String earTag) 
    {
        this.earTag = earTag;
    }

    public String getEarTag() 
    {
        return earTag;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }

    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }

    public void setArrivalDate(Date arrivalDate) 
    {
        this.arrivalDate = arrivalDate;
    }

    public Date getArrivalDate() 
    {
        return arrivalDate;
    }

    public void setWeaningDate(Date weaningDate) 
    {
        this.weaningDate = weaningDate;
    }

    public Date getWeaningDate() 
    {
        return weaningDate;
    }

    public void setGenotype(String genotype) 
    {
        this.genotype = genotype;
    }

    public String getGenotype() 
    {
        return genotype;
    }

    public void setBreedingStatus(String breedingStatus) 
    {
        this.breedingStatus = breedingStatus;
    }

    public String getBreedingStatus() 
    {
        return breedingStatus;
    }

    public void setFatherMouseId(Long fatherMouseId) 
    {
        this.fatherMouseId = fatherMouseId;
    }

    public Long getFatherMouseId() 
    {
        return fatherMouseId;
    }

    public void setMotherMouseId(Long motherMouseId) 
    {
        this.motherMouseId = motherMouseId;
    }

    public Long getMotherMouseId() 
    {
        return motherMouseId;
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
            .append("mouseId", getMouseId())
            .append("mouseCode", getMouseCode())
            .append("mouseName", getMouseName())
            .append("species", getSpecies())
            .append("strain", getStrain())
            .append("gender", getGender())
            .append("birthDate", getBirthDate())
            .append("weight", getWeight())
            .append("healthStatus", getHealthStatus())
            .append("photoUrl", getPhotoUrl())
            .append("rfidTag", getRfidTag())
            .append("earTag", getEarTag())
            .append("status", getStatus())
            .append("source", getSource())
            .append("supplier", getSupplier())
            .append("arrivalDate", getArrivalDate())
            .append("weaningDate", getWeaningDate())
            .append("genotype", getGenotype())
            .append("breedingStatus", getBreedingStatus())
            .append("fatherMouseId", getFatherMouseId())
            .append("motherMouseId", getMotherMouseId())
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

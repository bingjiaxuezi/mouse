package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验文件管理对象 sys_experiment_file
 * 
 * @author lczj
 * @date 2025-09-08
 */
public class SysExperimentFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private Long fileId;

    /** 实验ID */
    private Long experimentId;

    /** 关联实验记录ID */
    private Long recordId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 原始文件名 */
    private String fileOriginalName;

    /** 文件路径 */
    private String filePath;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件大小(字节) */
    private Long fileSize;

    /** 文件扩展名 */
    private String fileExtension;

    /** 文件分类 */
    @Excel(name = "文件分类")
    private String fileCategory;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String fileDescription;

    /** 文件标签(逗号分隔) */
    @Excel(name = "文件标签(逗号分隔)")
    private String fileTags;

    /** MinIO存储桶名称 */
    @Excel(name = "MinIO存储桶名称")
    private String bucketName;

    /** MinIO对象名称 */
    @Excel(name = "MinIO对象名称")
    private String objectName;

    /** 文件状态 */
    @Excel(name = "文件状态")
    private String fileStatus;

    /** 下载次数 */
    private Long downloadCount;

    /** 文件版本 */
    @Excel(name = "文件版本")
    private String version;

    /** 父文件ID(用于版本管理) */
    private Long parentFileId;

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
    @Excel(name = "创建者")
    private String buildBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildTime;

    /** 更新者 */
    private String modifyBy;

    /** 更新时间 */
    private Date modifyTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }

    public void setExperimentId(Long experimentId) 
    {
        this.experimentId = experimentId;
    }

    public Long getExperimentId() 
    {
        return experimentId;
    }

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setFileOriginalName(String fileOriginalName) 
    {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileOriginalName() 
    {
        return fileOriginalName;
    }

    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }

    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }

    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }

    public void setFileExtension(String fileExtension) 
    {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() 
    {
        return fileExtension;
    }

    public void setFileCategory(String fileCategory) 
    {
        this.fileCategory = fileCategory;
    }

    public String getFileCategory() 
    {
        return fileCategory;
    }

    public void setFileDescription(String fileDescription) 
    {
        this.fileDescription = fileDescription;
    }

    public String getFileDescription() 
    {
        return fileDescription;
    }

    public void setFileTags(String fileTags) 
    {
        this.fileTags = fileTags;
    }

    public String getFileTags() 
    {
        return fileTags;
    }

    public void setBucketName(String bucketName) 
    {
        this.bucketName = bucketName;
    }

    public String getBucketName() 
    {
        return bucketName;
    }

    public void setObjectName(String objectName) 
    {
        this.objectName = objectName;
    }

    public String getObjectName() 
    {
        return objectName;
    }

    public void setFileStatus(String fileStatus) 
    {
        this.fileStatus = fileStatus;
    }

    public String getFileStatus() 
    {
        return fileStatus;
    }

    public void setDownloadCount(Long downloadCount) 
    {
        this.downloadCount = downloadCount;
    }

    public Long getDownloadCount() 
    {
        return downloadCount;
    }

    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }

    public void setParentFileId(Long parentFileId) 
    {
        this.parentFileId = parentFileId;
    }

    public Long getParentFileId() 
    {
        return parentFileId;
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
            .append("fileId", getFileId())
            .append("experimentId", getExperimentId())
            .append("recordId", getRecordId())
            .append("fileName", getFileName())
            .append("fileOriginalName", getFileOriginalName())
            .append("filePath", getFilePath())
            .append("fileType", getFileType())
            .append("fileSize", getFileSize())
            .append("fileExtension", getFileExtension())
            .append("fileCategory", getFileCategory())
            .append("fileDescription", getFileDescription())
            .append("fileTags", getFileTags())
            .append("bucketName", getBucketName())
            .append("objectName", getObjectName())
            .append("fileStatus", getFileStatus())
            .append("downloadCount", getDownloadCount())
            .append("version", getVersion())
            .append("parentFileId", getParentFileId())
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

package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysExperimentFile;

/**
 * 实验文件管理Service接口
 * 
 * @author lczj
 * @date 2025-09-08
 */
public interface ISysExperimentFileService 
{
    /**
     * 查询实验文件管理
     * 
     * @param fileId 实验文件管理主键
     * @return 实验文件管理
     */
    public SysExperimentFile selectSysExperimentFileByFileId(Long fileId);

    /**
     * 查询实验文件管理列表
     * 
     * @param sysExperimentFile 实验文件管理
     * @return 实验文件管理集合
     */
    public List<SysExperimentFile> selectSysExperimentFileList(SysExperimentFile sysExperimentFile);

    /**
     * 新增实验文件管理
     * 
     * @param sysExperimentFile 实验文件管理
     * @return 结果
     */
    public int insertSysExperimentFile(SysExperimentFile sysExperimentFile);

    /**
     * 修改实验文件管理
     * 
     * @param sysExperimentFile 实验文件管理
     * @return 结果
     */
    public int updateSysExperimentFile(SysExperimentFile sysExperimentFile);

    /**
     * 批量删除实验文件管理
     * 
     * @param fileIds 需要删除的实验文件管理主键集合
     * @return 结果
     */
    public int deleteSysExperimentFileByFileIds(String fileIds);

    /**
     * 删除实验文件管理信息
     * 
     * @param fileId 实验文件管理主键
     * @return 结果
     */
    public int deleteSysExperimentFileByFileId(Long fileId);
}

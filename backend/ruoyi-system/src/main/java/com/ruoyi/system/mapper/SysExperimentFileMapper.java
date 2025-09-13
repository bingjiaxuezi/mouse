package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysExperimentFile;

/**
 * 实验文件管理Mapper接口
 * 
 * @author lczj
 * @date 2025-09-08
 */
public interface SysExperimentFileMapper 
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
     * 删除实验文件管理
     * 
     * @param fileId 实验文件管理主键
     * @return 结果
     */
    public int deleteSysExperimentFileByFileId(Long fileId);

    /**
     * 批量删除实验文件管理
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExperimentFileByFileIds(String[] fileIds);
}

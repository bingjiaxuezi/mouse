package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysExperimentFileMapper;
import com.ruoyi.system.domain.SysExperimentFile;
import com.ruoyi.system.service.ISysExperimentFileService;
import com.ruoyi.common.core.text.Convert;

/**
 * 实验文件管理Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-08
 */
@Service
public class SysExperimentFileServiceImpl implements ISysExperimentFileService 
{
    @Autowired
    private SysExperimentFileMapper sysExperimentFileMapper;

    /**
     * 查询实验文件管理
     * 
     * @param fileId 实验文件管理主键
     * @return 实验文件管理
     */
    @Override
    public SysExperimentFile selectSysExperimentFileByFileId(Long fileId)
    {
        return sysExperimentFileMapper.selectSysExperimentFileByFileId(fileId);
    }

    /**
     * 查询实验文件管理列表
     * 
     * @param sysExperimentFile 实验文件管理
     * @return 实验文件管理
     */
    @Override
    public List<SysExperimentFile> selectSysExperimentFileList(SysExperimentFile sysExperimentFile)
    {
        return sysExperimentFileMapper.selectSysExperimentFileList(sysExperimentFile);
    }

    /**
     * 新增实验文件管理
     * 
     * @param sysExperimentFile 实验文件管理
     * @return 结果
     */
    @Override
    public int insertSysExperimentFile(SysExperimentFile sysExperimentFile)
    {
        return sysExperimentFileMapper.insertSysExperimentFile(sysExperimentFile);
    }

    /**
     * 修改实验文件管理
     * 
     * @param sysExperimentFile 实验文件管理
     * @return 结果
     */
    @Override
    public int updateSysExperimentFile(SysExperimentFile sysExperimentFile)
    {
        return sysExperimentFileMapper.updateSysExperimentFile(sysExperimentFile);
    }

    /**
     * 批量删除实验文件管理
     * 
     * @param fileIds 需要删除的实验文件管理主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentFileByFileIds(String fileIds)
    {
        return sysExperimentFileMapper.deleteSysExperimentFileByFileIds(Convert.toStrArray(fileIds));
    }

    /**
     * 删除实验文件管理信息
     * 
     * @param fileId 实验文件管理主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentFileByFileId(Long fileId)
    {
        return sysExperimentFileMapper.deleteSysExperimentFileByFileId(fileId);
    }
}

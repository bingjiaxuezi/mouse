package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysExperimentCageMapper;
import com.ruoyi.system.domain.SysExperimentCage;
import com.ruoyi.system.domain.vo.SysExperimentCageVO;
import com.ruoyi.system.service.ISysExperimentCageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 实验笼子关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-07
 */
@Service
public class SysExperimentCageServiceImpl implements ISysExperimentCageService 
{
    @Autowired
    private SysExperimentCageMapper sysExperimentCageMapper;

    /**
     * 查询实验笼子关系
     * 
     * @param relationId 实验笼子关系主键
     * @return 实验笼子关系
     */
    @Override
    public SysExperimentCage selectSysExperimentCageByRelationId(Long relationId)
    {
        return sysExperimentCageMapper.selectSysExperimentCageByRelationId(relationId);
    }

    /**
     * 查询实验笼子关系列表
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 实验笼子关系
     */
    @Override
    public List<SysExperimentCage> selectSysExperimentCageList(SysExperimentCage sysExperimentCage)
    {
        return sysExperimentCageMapper.selectSysExperimentCageList(sysExperimentCage);
    }

    /**
     * 新增实验笼子关系
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 结果
     */
    @Override
    public int insertSysExperimentCage(SysExperimentCage sysExperimentCage)
    {
        return sysExperimentCageMapper.insertSysExperimentCage(sysExperimentCage);
    }

    /**
     * 修改实验笼子关系
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 结果
     */
    @Override
    public int updateSysExperimentCage(SysExperimentCage sysExperimentCage)
    {
        return sysExperimentCageMapper.updateSysExperimentCage(sysExperimentCage);
    }

    /**
     * 批量删除实验笼子关系
     * 
     * @param relationIds 需要删除的实验笼子关系主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentCageByRelationIds(String relationIds)
    {
        return sysExperimentCageMapper.deleteSysExperimentCageByRelationIds(Convert.toStrArray(relationIds));
    }

    /**
     * 删除实验笼子关系信息
     * 
     * @param relationId 实验笼子关系主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentCageByRelationId(Long relationId)
    {
        return sysExperimentCageMapper.deleteSysExperimentCageByRelationId(relationId);
    }

    /**
     * 查询实验笼子关系列表（包含笼子详细信息）
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 实验笼子关系集合
     */
    @Override
    public List<SysExperimentCageVO> selectSysExperimentCageVOList(SysExperimentCage sysExperimentCage)
    {
        return sysExperimentCageMapper.selectSysExperimentCageVOList(sysExperimentCage);
    }
}

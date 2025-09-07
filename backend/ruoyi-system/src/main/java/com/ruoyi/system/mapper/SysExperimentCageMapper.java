package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysExperimentCage;
import com.ruoyi.system.domain.vo.SysExperimentCageVO;

/**
 * 实验笼子关系Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-07
 */
public interface SysExperimentCageMapper 
{
    /**
     * 查询实验笼子关系
     * 
     * @param relationId 实验笼子关系主键
     * @return 实验笼子关系
     */
    public SysExperimentCage selectSysExperimentCageByRelationId(Long relationId);

    /**
     * 查询实验笼子关系列表
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 实验笼子关系集合
     */
    public List<SysExperimentCage> selectSysExperimentCageList(SysExperimentCage sysExperimentCage);

    /**
     * 新增实验笼子关系
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 结果
     */
    public int insertSysExperimentCage(SysExperimentCage sysExperimentCage);

    /**
     * 修改实验笼子关系
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 结果
     */
    public int updateSysExperimentCage(SysExperimentCage sysExperimentCage);

    /**
     * 删除实验笼子关系
     * 
     * @param relationId 实验笼子关系主键
     * @return 结果
     */
    public int deleteSysExperimentCageByRelationId(Long relationId);

    /**
     * 批量删除实验笼子关系
     * 
     * @param relationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExperimentCageByRelationIds(String[] relationIds);

    /**
     * 查询实验笼子关系列表（包含笼子详细信息）
     * 
     * @param sysExperimentCage 实验笼子关系
     * @return 实验笼子关系集合
     */
    public List<SysExperimentCageVO> selectSysExperimentCageVOList(SysExperimentCage sysExperimentCage);
}

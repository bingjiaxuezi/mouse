package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysExperimentMapper;
import com.ruoyi.system.domain.SysExperiment;
import com.ruoyi.system.service.ISysExperimentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 实验详情展示Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
@Service
public class SysExperimentServiceImpl implements ISysExperimentService 
{
    @Autowired
    private SysExperimentMapper sysExperimentMapper;

    /**
     * 查询实验详情展示
     * 
     * @param experimentId 实验详情展示主键
     * @return 实验详情展示
     */
    @Override
    public SysExperiment selectSysExperimentByExperimentId(Long experimentId)
    {
        return sysExperimentMapper.selectSysExperimentByExperimentId(experimentId);
    }

    /**
     * 查询实验详情展示列表
     * 
     * @param sysExperiment 实验详情展示
     * @return 实验详情展示
     */
    @Override
    public List<SysExperiment> selectSysExperimentList(SysExperiment sysExperiment)
    {
        return sysExperimentMapper.selectSysExperimentList(sysExperiment);
    }

    /**
     * 新增实验详情展示
     * 
     * @param sysExperiment 实验详情展示
     * @return 结果
     */
    @Override
    public int insertSysExperiment(SysExperiment sysExperiment)
    {
        return sysExperimentMapper.insertSysExperiment(sysExperiment);
    }

    /**
     * 修改实验详情展示
     * 
     * @param sysExperiment 实验详情展示
     * @return 结果
     */
    @Override
    public int updateSysExperiment(SysExperiment sysExperiment)
    {
        return sysExperimentMapper.updateSysExperiment(sysExperiment);
    }

    /**
     * 批量删除实验详情展示
     * 
     * @param experimentIds 需要删除的实验详情展示主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentByExperimentIds(String experimentIds)
    {
        return sysExperimentMapper.deleteSysExperimentByExperimentIds(Convert.toStrArray(experimentIds));
    }

    /**
     * 删除实验详情展示信息
     * 
     * @param experimentId 实验详情展示主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentByExperimentId(Long experimentId)
    {
        return sysExperimentMapper.deleteSysExperimentByExperimentId(experimentId);
    }
}

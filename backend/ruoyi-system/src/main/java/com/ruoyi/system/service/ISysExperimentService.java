package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysExperiment;

/**
 * 实验详情展示Service接口
 * 
 * @author ruoyi
 * @date 2025-09-06
 */
public interface ISysExperimentService 
{
    /**
     * 查询实验详情展示
     * 
     * @param experimentId 实验详情展示主键
     * @return 实验详情展示
     */
    public SysExperiment selectSysExperimentByExperimentId(Long experimentId);

    /**
     * 查询实验详情展示列表
     * 
     * @param sysExperiment 实验详情展示
     * @return 实验详情展示集合
     */
    public List<SysExperiment> selectSysExperimentList(SysExperiment sysExperiment);

    /**
     * 新增实验详情展示
     * 
     * @param sysExperiment 实验详情展示
     * @return 结果
     */
    public int insertSysExperiment(SysExperiment sysExperiment);

    /**
     * 修改实验详情展示
     * 
     * @param sysExperiment 实验详情展示
     * @return 结果
     */
    public int updateSysExperiment(SysExperiment sysExperiment);

    /**
     * 批量删除实验详情展示
     * 
     * @param experimentIds 需要删除的实验详情展示主键集合
     * @return 结果
     */
    public int deleteSysExperimentByExperimentIds(String experimentIds);

    /**
     * 删除实验详情展示信息
     * 
     * @param experimentId 实验详情展示主键
     * @return 结果
     */
    public int deleteSysExperimentByExperimentId(Long experimentId);
}

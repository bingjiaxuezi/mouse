package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorEnvironment;

/**
 * 行为事件环境数据Service接口
 * 
 * @author lczj
 * @date 2025-10-07
 */
public interface ISysBehaviorEnvironmentService 
{
    /**
     * 查询行为事件环境数据
     * 
     * @param envId 行为事件环境数据主键
     * @return 行为事件环境数据
     */
    public SysBehaviorEnvironment selectSysBehaviorEnvironmentByEnvId(Long envId);

    /**
     * 查询行为事件环境数据列表
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据
     * @return 行为事件环境数据集合
     */
    public List<SysBehaviorEnvironment> selectSysBehaviorEnvironmentList(SysBehaviorEnvironment sysBehaviorEnvironment);

    /**
     * 新增行为事件环境数据
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据
     * @return 结果
     */
    public int insertSysBehaviorEnvironment(SysBehaviorEnvironment sysBehaviorEnvironment);

    /**
     * 修改行为事件环境数据
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据
     * @return 结果
     */
    public int updateSysBehaviorEnvironment(SysBehaviorEnvironment sysBehaviorEnvironment);

    /**
     * 批量删除行为事件环境数据
     * 
     * @param envIds 需要删除的行为事件环境数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorEnvironmentByEnvIds(String envIds);

    /**
     * 删除行为事件环境数据信息
     * 
     * @param envId 行为事件环境数据主键
     * @return 结果
     */
    public int deleteSysBehaviorEnvironmentByEnvId(Long envId);
}

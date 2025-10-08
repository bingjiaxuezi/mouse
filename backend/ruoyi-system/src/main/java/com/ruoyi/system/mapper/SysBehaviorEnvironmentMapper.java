package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorEnvironment;
import com.ruoyi.system.domain.SysBehaviorEvent;

/**
 * 行为事件环境数据Mapper接口
 * 
 * @author lczj
 * @date 2025-10-07
 */
public interface SysBehaviorEnvironmentMapper 
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
     * 删除行为事件环境数据
     * 
     * @param envId 行为事件环境数据主键
     * @return 结果
     */
    public int deleteSysBehaviorEnvironmentByEnvId(Long envId);

    /**
     * 批量删除行为事件环境数据
     * 
     * @param envIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorEnvironmentByEnvIds(String[] envIds);

    /**
     * 批量删除小鼠行为事件核心（记录具体行为事件）
     * 
     * @param envIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorEventByEventIds(String[] envIds);
    
    /**
     * 批量新增小鼠行为事件核心（记录具体行为事件）
     * 
     * @param sysBehaviorEventList 小鼠行为事件核心（记录具体行为事件）列表
     * @return 结果
     */
    public int batchSysBehaviorEvent(List<SysBehaviorEvent> sysBehaviorEventList);
    

    /**
     * 通过行为事件环境数据主键删除小鼠行为事件核心（记录具体行为事件）信息
     * 
     * @param envId 行为事件环境数据ID
     * @return 结果
     */
    public int deleteSysBehaviorEventByEventId(Long envId);
}

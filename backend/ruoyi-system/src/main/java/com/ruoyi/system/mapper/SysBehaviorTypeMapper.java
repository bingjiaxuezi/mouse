package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorType;

/**
 * 行为类型字典Mapper接口
 * 
 * @author lczj
 * @date 2025-09-20
 */
public interface SysBehaviorTypeMapper 
{
    /**
     * 查询行为类型字典
     * 
     * @param behaviorTypeId 行为类型字典主键
     * @return 行为类型字典
     */
    public SysBehaviorType selectSysBehaviorTypeByBehaviorTypeId(Long behaviorTypeId);

    /**
     * 查询行为类型字典列表
     * 
     * @param sysBehaviorType 行为类型字典
     * @return 行为类型字典集合
     */
    public List<SysBehaviorType> selectSysBehaviorTypeList(SysBehaviorType sysBehaviorType);

    /**
     * 新增行为类型字典
     * 
     * @param sysBehaviorType 行为类型字典
     * @return 结果
     */
    public int insertSysBehaviorType(SysBehaviorType sysBehaviorType);

    /**
     * 修改行为类型字典
     * 
     * @param sysBehaviorType 行为类型字典
     * @return 结果
     */
    public int updateSysBehaviorType(SysBehaviorType sysBehaviorType);

    /**
     * 删除行为类型字典
     * 
     * @param behaviorTypeId 行为类型字典主键
     * @return 结果
     */
    public int deleteSysBehaviorTypeByBehaviorTypeId(Long behaviorTypeId);

    /**
     * 批量删除行为类型字典
     * 
     * @param behaviorTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorTypeByBehaviorTypeIds(String[] behaviorTypeIds);
}

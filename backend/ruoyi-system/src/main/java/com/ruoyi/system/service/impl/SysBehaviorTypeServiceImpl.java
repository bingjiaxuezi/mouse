package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysBehaviorTypeMapper;
import com.ruoyi.system.domain.SysBehaviorType;
import com.ruoyi.system.service.ISysBehaviorTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 行为类型字典Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-20
 */
@Service
public class SysBehaviorTypeServiceImpl implements ISysBehaviorTypeService 
{
    @Autowired
    private SysBehaviorTypeMapper sysBehaviorTypeMapper;

    /**
     * 查询行为类型字典
     * 
     * @param behaviorTypeId 行为类型字典主键
     * @return 行为类型字典
     */
    @Override
    public SysBehaviorType selectSysBehaviorTypeByBehaviorTypeId(Long behaviorTypeId)
    {
        return sysBehaviorTypeMapper.selectSysBehaviorTypeByBehaviorTypeId(behaviorTypeId);
    }

    /**
     * 查询行为类型字典列表
     * 
     * @param sysBehaviorType 行为类型字典
     * @return 行为类型字典
     */
    @Override
    public List<SysBehaviorType> selectSysBehaviorTypeList(SysBehaviorType sysBehaviorType)
    {
        return sysBehaviorTypeMapper.selectSysBehaviorTypeList(sysBehaviorType);
    }

    /**
     * 新增行为类型字典
     * 
     * @param sysBehaviorType 行为类型字典
     * @return 结果
     */
    @Override
    public int insertSysBehaviorType(SysBehaviorType sysBehaviorType)
    {
        sysBehaviorType.setCreateTime(DateUtils.getNowDate());
        return sysBehaviorTypeMapper.insertSysBehaviorType(sysBehaviorType);
    }

    /**
     * 修改行为类型字典
     * 
     * @param sysBehaviorType 行为类型字典
     * @return 结果
     */
    @Override
    public int updateSysBehaviorType(SysBehaviorType sysBehaviorType)
    {
        sysBehaviorType.setUpdateTime(DateUtils.getNowDate());
        return sysBehaviorTypeMapper.updateSysBehaviorType(sysBehaviorType);
    }

    /**
     * 批量删除行为类型字典
     * 
     * @param behaviorTypeIds 需要删除的行为类型字典主键
     * @return 结果
     */
    @Override
    public int deleteSysBehaviorTypeByBehaviorTypeIds(String behaviorTypeIds)
    {
        return sysBehaviorTypeMapper.deleteSysBehaviorTypeByBehaviorTypeIds(Convert.toStrArray(behaviorTypeIds));
    }

    /**
     * 删除行为类型字典信息
     * 
     * @param behaviorTypeId 行为类型字典主键
     * @return 结果
     */
    @Override
    public int deleteSysBehaviorTypeByBehaviorTypeId(Long behaviorTypeId)
    {
        return sysBehaviorTypeMapper.deleteSysBehaviorTypeByBehaviorTypeId(behaviorTypeId);
    }
}

package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SysBehaviorEvent;
import com.ruoyi.system.mapper.SysBehaviorEnvironmentMapper;
import com.ruoyi.system.domain.SysBehaviorEnvironment;
import com.ruoyi.system.service.ISysBehaviorEnvironmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 行为事件环境数据Service业务层处理
 * 
 * @author lczj
 * @date 2025-10-07
 */
@Service
public class SysBehaviorEnvironmentServiceImpl implements ISysBehaviorEnvironmentService 
{
    @Autowired
    private SysBehaviorEnvironmentMapper sysBehaviorEnvironmentMapper;

    /**
     * 查询行为事件环境数据
     * 
     * @param envId 行为事件环境数据主键
     * @return 行为事件环境数据
     */
    @Override
    public SysBehaviorEnvironment selectSysBehaviorEnvironmentByEnvId(Long envId)
    {
        return sysBehaviorEnvironmentMapper.selectSysBehaviorEnvironmentByEnvId(envId);
    }

    /**
     * 查询行为事件环境数据列表
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据
     * @return 行为事件环境数据
     */
    @Override
    public List<SysBehaviorEnvironment> selectSysBehaviorEnvironmentList(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        return sysBehaviorEnvironmentMapper.selectSysBehaviorEnvironmentList(sysBehaviorEnvironment);
    }

    /**
     * 新增行为事件环境数据
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysBehaviorEnvironment(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        int rows = sysBehaviorEnvironmentMapper.insertSysBehaviorEnvironment(sysBehaviorEnvironment);
        insertSysBehaviorEvent(sysBehaviorEnvironment);
        return rows;
    }

    /**
     * 修改行为事件环境数据
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysBehaviorEnvironment(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        sysBehaviorEnvironmentMapper.deleteSysBehaviorEventByEventId(sysBehaviorEnvironment.getEnvId());
        insertSysBehaviorEvent(sysBehaviorEnvironment);
        return sysBehaviorEnvironmentMapper.updateSysBehaviorEnvironment(sysBehaviorEnvironment);
    }

    /**
     * 批量删除行为事件环境数据
     * 
     * @param envIds 需要删除的行为事件环境数据主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysBehaviorEnvironmentByEnvIds(String envIds)
    {
        sysBehaviorEnvironmentMapper.deleteSysBehaviorEventByEventIds(Convert.toStrArray(envIds));
        return sysBehaviorEnvironmentMapper.deleteSysBehaviorEnvironmentByEnvIds(Convert.toStrArray(envIds));
    }

    /**
     * 删除行为事件环境数据信息
     * 
     * @param envId 行为事件环境数据主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysBehaviorEnvironmentByEnvId(Long envId)
    {
        sysBehaviorEnvironmentMapper.deleteSysBehaviorEventByEventId(envId);
        return sysBehaviorEnvironmentMapper.deleteSysBehaviorEnvironmentByEnvId(envId);
    }

    /**
     * 新增小鼠行为事件核心（记录具体行为事件）信息
     * 
     * @param sysBehaviorEnvironment 行为事件环境数据对象
     */
    public void insertSysBehaviorEvent(SysBehaviorEnvironment sysBehaviorEnvironment)
    {
        List<SysBehaviorEvent> sysBehaviorEventList = sysBehaviorEnvironment.getSysBehaviorEventList();
        Long envId = sysBehaviorEnvironment.getEnvId();
        if (StringUtils.isNotNull(sysBehaviorEventList))
        {
            List<SysBehaviorEvent> list = new ArrayList<SysBehaviorEvent>();
            for (SysBehaviorEvent sysBehaviorEvent : sysBehaviorEventList)
            {
                sysBehaviorEvent.setEventId(envId);
                list.add(sysBehaviorEvent);
            }
            if (list.size() > 0)
            {
                sysBehaviorEnvironmentMapper.batchSysBehaviorEvent(list);
            }
        }
    }
}

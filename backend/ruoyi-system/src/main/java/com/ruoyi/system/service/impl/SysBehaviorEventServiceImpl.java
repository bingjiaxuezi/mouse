package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SysBehaviorSession;
import com.ruoyi.system.mapper.SysBehaviorEventMapper;
import com.ruoyi.system.domain.SysBehaviorEvent;
import com.ruoyi.system.service.ISysBehaviorEventService;
import com.ruoyi.common.core.text.Convert;

/**
 * 小鼠行为事件核心（记录具体行为事件）Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-27
 */
@Service
public class SysBehaviorEventServiceImpl implements ISysBehaviorEventService 
{
    @Autowired
    private SysBehaviorEventMapper sysBehaviorEventMapper;

    /**
     * 查询小鼠行为事件核心（记录具体行为事件）
     * 
     * @param eventId 小鼠行为事件核心（记录具体行为事件）主键
     * @return 小鼠行为事件核心（记录具体行为事件）
     */
    @Override
    public SysBehaviorEvent selectSysBehaviorEventByEventId(Long eventId)
    {
        return sysBehaviorEventMapper.selectSysBehaviorEventByEventId(eventId);
    }

    /**
     * 查询小鼠行为事件核心（记录具体行为事件）列表
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）
     * @return 小鼠行为事件核心（记录具体行为事件）
     */
    @Override
    public List<SysBehaviorEvent> selectSysBehaviorEventList(SysBehaviorEvent sysBehaviorEvent)
    {
        return sysBehaviorEventMapper.selectSysBehaviorEventList(sysBehaviorEvent);
    }

    /**
     * 新增小鼠行为事件核心（记录具体行为事件）
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysBehaviorEvent(SysBehaviorEvent sysBehaviorEvent)
    {
        int rows = sysBehaviorEventMapper.insertSysBehaviorEvent(sysBehaviorEvent);
        insertSysBehaviorSession(sysBehaviorEvent);
        return rows;
    }

    /**
     * 修改小鼠行为事件核心（记录具体行为事件）
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysBehaviorEvent(SysBehaviorEvent sysBehaviorEvent)
    {
        sysBehaviorEventMapper.deleteSysBehaviorSessionBySessionId(sysBehaviorEvent.getEventId());
        insertSysBehaviorSession(sysBehaviorEvent);
        return sysBehaviorEventMapper.updateSysBehaviorEvent(sysBehaviorEvent);
    }

    /**
     * 批量删除小鼠行为事件核心（记录具体行为事件）
     * 
     * @param eventIds 需要删除的小鼠行为事件核心（记录具体行为事件）主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysBehaviorEventByEventIds(String eventIds)
    {
        sysBehaviorEventMapper.deleteSysBehaviorSessionBySessionIds(Convert.toStrArray(eventIds));
        return sysBehaviorEventMapper.deleteSysBehaviorEventByEventIds(Convert.toStrArray(eventIds));
    }

    /**
     * 删除小鼠行为事件核心（记录具体行为事件）信息
     * 
     * @param eventId 小鼠行为事件核心（记录具体行为事件）主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysBehaviorEventByEventId(Long eventId)
    {
        sysBehaviorEventMapper.deleteSysBehaviorSessionBySessionId(eventId);
        return sysBehaviorEventMapper.deleteSysBehaviorEventByEventId(eventId);
    }

    /**
     * 新增小鼠行为监测会话信息
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）对象
     */
    public void insertSysBehaviorSession(SysBehaviorEvent sysBehaviorEvent)
    {
        List<SysBehaviorSession> sysBehaviorSessionList = sysBehaviorEvent.getSysBehaviorSessionList();
        Long eventId = sysBehaviorEvent.getEventId();
        if (StringUtils.isNotNull(sysBehaviorSessionList))
        {
            List<SysBehaviorSession> list = new ArrayList<SysBehaviorSession>();
            for (SysBehaviorSession sysBehaviorSession : sysBehaviorSessionList)
            {
                sysBehaviorSession.setSessionId(eventId);
                list.add(sysBehaviorSession);
            }
            if (list.size() > 0)
            {
                sysBehaviorEventMapper.batchSysBehaviorSession(list);
            }
        }
    }
}

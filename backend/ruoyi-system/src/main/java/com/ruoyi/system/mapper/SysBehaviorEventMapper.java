package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorEvent;
import com.ruoyi.system.domain.SysBehaviorSession;

/**
 * 小鼠行为事件核心（记录具体行为事件）Mapper接口
 * 
 * @author lczj
 * @date 2025-09-27
 */
public interface SysBehaviorEventMapper 
{
    /**
     * 查询小鼠行为事件核心（记录具体行为事件）
     * 
     * @param eventId 小鼠行为事件核心（记录具体行为事件）主键
     * @return 小鼠行为事件核心（记录具体行为事件）
     */
    public SysBehaviorEvent selectSysBehaviorEventByEventId(Long eventId);

    /**
     * 查询小鼠行为事件核心（记录具体行为事件）列表
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）
     * @return 小鼠行为事件核心（记录具体行为事件）集合
     */
    public List<SysBehaviorEvent> selectSysBehaviorEventList(SysBehaviorEvent sysBehaviorEvent);

    /**
     * 新增小鼠行为事件核心（记录具体行为事件）
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）
     * @return 结果
     */
    public int insertSysBehaviorEvent(SysBehaviorEvent sysBehaviorEvent);

    /**
     * 修改小鼠行为事件核心（记录具体行为事件）
     * 
     * @param sysBehaviorEvent 小鼠行为事件核心（记录具体行为事件）
     * @return 结果
     */
    public int updateSysBehaviorEvent(SysBehaviorEvent sysBehaviorEvent);

    /**
     * 删除小鼠行为事件核心（记录具体行为事件）
     * 
     * @param eventId 小鼠行为事件核心（记录具体行为事件）主键
     * @return 结果
     */
    public int deleteSysBehaviorEventByEventId(Long eventId);

    /**
     * 批量删除小鼠行为事件核心（记录具体行为事件）
     * 
     * @param eventIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorEventByEventIds(String[] eventIds);

    /**
     * 批量删除小鼠行为监测会话
     * 
     * @param eventIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorSessionBySessionIds(String[] eventIds);
    
    /**
     * 批量新增小鼠行为监测会话
     * 
     * @param sysBehaviorSessionList 小鼠行为监测会话列表
     * @return 结果
     */
    public int batchSysBehaviorSession(List<SysBehaviorSession> sysBehaviorSessionList);
    

    /**
     * 通过小鼠行为事件核心（记录具体行为事件）主键删除小鼠行为监测会话信息
     * 
     * @param eventId 小鼠行为事件核心（记录具体行为事件）ID
     * @return 结果
     */
    public int deleteSysBehaviorSessionBySessionId(Long eventId);
}

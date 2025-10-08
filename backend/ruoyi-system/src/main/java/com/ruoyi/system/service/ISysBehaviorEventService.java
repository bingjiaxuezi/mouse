package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorEvent;

/**
 * 小鼠行为事件核心（记录具体行为事件）Service接口
 * 
 * @author lczj
 * @date 2025-09-27
 */
public interface ISysBehaviorEventService 
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
     * 批量删除小鼠行为事件核心（记录具体行为事件）
     * 
     * @param eventIds 需要删除的小鼠行为事件核心（记录具体行为事件）主键集合
     * @return 结果
     */
    public int deleteSysBehaviorEventByEventIds(String eventIds);

    /**
     * 删除小鼠行为事件核心（记录具体行为事件）信息
     * 
     * @param eventId 小鼠行为事件核心（记录具体行为事件）主键
     * @return 结果
     */
    public int deleteSysBehaviorEventByEventId(Long eventId);
}

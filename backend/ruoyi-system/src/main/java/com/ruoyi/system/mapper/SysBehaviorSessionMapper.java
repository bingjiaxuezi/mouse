package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorSession;

/**
 * 小鼠行为监测会话Mapper接口
 * 
 * @author lczj
 * @date 2025-09-21
 */
public interface SysBehaviorSessionMapper 
{
    /**
     * 查询小鼠行为监测会话
     * 
     * @param sessionId 小鼠行为监测会话主键
     * @return 小鼠行为监测会话
     */
    public SysBehaviorSession selectSysBehaviorSessionBySessionId(Long sessionId);

    /**
     * 查询小鼠行为监测会话列表
     * 
     * @param sysBehaviorSession 小鼠行为监测会话
     * @return 小鼠行为监测会话集合
     */
    public List<SysBehaviorSession> selectSysBehaviorSessionList(SysBehaviorSession sysBehaviorSession);

    /**
     * 新增小鼠行为监测会话
     * 
     * @param sysBehaviorSession 小鼠行为监测会话
     * @return 结果
     */
    public int insertSysBehaviorSession(SysBehaviorSession sysBehaviorSession);

    /**
     * 修改小鼠行为监测会话
     * 
     * @param sysBehaviorSession 小鼠行为监测会话
     * @return 结果
     */
    public int updateSysBehaviorSession(SysBehaviorSession sysBehaviorSession);

    /**
     * 删除小鼠行为监测会话
     * 
     * @param sessionId 小鼠行为监测会话主键
     * @return 结果
     */
    public int deleteSysBehaviorSessionBySessionId(Long sessionId);

    /**
     * 批量删除小鼠行为监测会话
     * 
     * @param sessionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBehaviorSessionBySessionIds(String[] sessionIds);
}

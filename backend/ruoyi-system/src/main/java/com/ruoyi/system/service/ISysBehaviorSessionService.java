package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysBehaviorSession;

/**
 * 小鼠行为监测会话Service接口
 * 
 * @author lczj
 * @date 2025-09-21
 */
public interface ISysBehaviorSessionService 
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
     * 批量删除小鼠行为监测会话
     * 
     * @param sessionIds 需要删除的小鼠行为监测会话主键集合
     * @return 结果
     */
    public int deleteSysBehaviorSessionBySessionIds(String sessionIds);

    /**
     * 删除小鼠行为监测会话信息
     * 
     * @param sessionId 小鼠行为监测会话主键
     * @return 结果
     */
    public int deleteSysBehaviorSessionBySessionId(Long sessionId);
}

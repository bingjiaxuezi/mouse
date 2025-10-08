package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysBehaviorSessionMapper;
import com.ruoyi.system.domain.SysBehaviorSession;
import com.ruoyi.system.service.ISysBehaviorSessionService;
import com.ruoyi.common.core.text.Convert;

/**
 * 小鼠行为监测会话Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-21
 */
@Service
public class SysBehaviorSessionServiceImpl implements ISysBehaviorSessionService 
{
    @Autowired
    private SysBehaviorSessionMapper sysBehaviorSessionMapper;

    /**
     * 查询小鼠行为监测会话
     * 
     * @param sessionId 小鼠行为监测会话主键
     * @return 小鼠行为监测会话
     */
    @Override
    public SysBehaviorSession selectSysBehaviorSessionBySessionId(Long sessionId)
    {
        return sysBehaviorSessionMapper.selectSysBehaviorSessionBySessionId(sessionId);
    }

    /**
     * 查询小鼠行为监测会话列表
     * 
     * @param sysBehaviorSession 小鼠行为监测会话
     * @return 小鼠行为监测会话
     */
    @Override
    public List<SysBehaviorSession> selectSysBehaviorSessionList(SysBehaviorSession sysBehaviorSession)
    {
        return sysBehaviorSessionMapper.selectSysBehaviorSessionList(sysBehaviorSession);
    }

    /**
     * 新增小鼠行为监测会话
     * 
     * @param sysBehaviorSession 小鼠行为监测会话
     * @return 结果
     */
    @Override
    public int insertSysBehaviorSession(SysBehaviorSession sysBehaviorSession)
    {
        return sysBehaviorSessionMapper.insertSysBehaviorSession(sysBehaviorSession);
    }

    /**
     * 修改小鼠行为监测会话
     * 
     * @param sysBehaviorSession 小鼠行为监测会话
     * @return 结果
     */
    @Override
    public int updateSysBehaviorSession(SysBehaviorSession sysBehaviorSession)
    {
        return sysBehaviorSessionMapper.updateSysBehaviorSession(sysBehaviorSession);
    }

    /**
     * 批量删除小鼠行为监测会话
     * 
     * @param sessionIds 需要删除的小鼠行为监测会话主键
     * @return 结果
     */
    @Override
    public int deleteSysBehaviorSessionBySessionIds(String sessionIds)
    {
        return sysBehaviorSessionMapper.deleteSysBehaviorSessionBySessionIds(Convert.toStrArray(sessionIds));
    }

    /**
     * 删除小鼠行为监测会话信息
     * 
     * @param sessionId 小鼠行为监测会话主键
     * @return 结果
     */
    @Override
    public int deleteSysBehaviorSessionBySessionId(Long sessionId)
    {
        return sysBehaviorSessionMapper.deleteSysBehaviorSessionBySessionId(sessionId);
    }
}

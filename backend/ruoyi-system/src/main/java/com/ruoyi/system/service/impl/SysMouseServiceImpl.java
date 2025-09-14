package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMouseMapper;
import com.ruoyi.system.domain.SysMouse;
import com.ruoyi.system.service.ISysMouseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 小鼠基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-13
 */
@Service
public class SysMouseServiceImpl implements ISysMouseService 
{
    @Autowired
    private SysMouseMapper sysMouseMapper;

    /**
     * 查询小鼠基础信息
     * 
     * @param mouseId 小鼠基础信息主键
     * @return 小鼠基础信息
     */
    @Override
    public SysMouse selectSysMouseByMouseId(Long mouseId)
    {
        return sysMouseMapper.selectSysMouseByMouseId(mouseId);
    }

    /**
     * 查询小鼠基础信息列表
     * 
     * @param sysMouse 小鼠基础信息
     * @return 小鼠基础信息
     */
    @Override
    public List<SysMouse> selectSysMouseList(SysMouse sysMouse)
    {
        return sysMouseMapper.selectSysMouseList(sysMouse);
    }

    /**
     * 新增小鼠基础信息
     * 
     * @param sysMouse 小鼠基础信息
     * @return 结果
     */
    @Override
    public int insertSysMouse(SysMouse sysMouse)
    {
        // 如果小鼠编号为空，自动生成UUID
        if (sysMouse.getMouseCode() == null || sysMouse.getMouseCode().trim().isEmpty()) {
            sysMouse.setMouseCode(java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 16));
        }
        
        sysMouse.setCreateTime(DateUtils.getNowDate());
        return sysMouseMapper.insertSysMouse(sysMouse);
    }

    /**
     * 修改小鼠基础信息
     * 
     * @param sysMouse 小鼠基础信息
     * @return 结果
     */
    @Override
    public int updateSysMouse(SysMouse sysMouse)
    {
        sysMouse.setUpdateTime(DateUtils.getNowDate());
        return sysMouseMapper.updateSysMouse(sysMouse);
    }

    /**
     * 批量删除小鼠基础信息
     * 
     * @param mouseIds 需要删除的小鼠基础信息主键
     * @return 结果
     */
    @Override
    public int deleteSysMouseByMouseIds(String mouseIds)
    {
        return sysMouseMapper.deleteSysMouseByMouseIds(Convert.toStrArray(mouseIds));
    }

    /**
     * 删除小鼠基础信息信息
     * 
     * @param mouseId 小鼠基础信息主键
     * @return 结果
     */
    @Override
    public int deleteSysMouseByMouseId(Long mouseId)
    {
        return sysMouseMapper.deleteSysMouseByMouseId(mouseId);
    }
}

package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysMouse;

/**
 * 小鼠基础信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-13
 */
public interface SysMouseMapper 
{
    /**
     * 查询小鼠基础信息
     * 
     * @param mouseId 小鼠基础信息主键
     * @return 小鼠基础信息
     */
    public SysMouse selectSysMouseByMouseId(Long mouseId);

    /**
     * 查询小鼠基础信息列表
     * 
     * @param sysMouse 小鼠基础信息
     * @return 小鼠基础信息集合
     */
    public List<SysMouse> selectSysMouseList(SysMouse sysMouse);

    /**
     * 新增小鼠基础信息
     * 
     * @param sysMouse 小鼠基础信息
     * @return 结果
     */
    public int insertSysMouse(SysMouse sysMouse);

    /**
     * 修改小鼠基础信息
     * 
     * @param sysMouse 小鼠基础信息
     * @return 结果
     */
    public int updateSysMouse(SysMouse sysMouse);

    /**
     * 删除小鼠基础信息
     * 
     * @param mouseId 小鼠基础信息主键
     * @return 结果
     */
    public int deleteSysMouseByMouseId(Long mouseId);

    /**
     * 批量删除小鼠基础信息
     * 
     * @param mouseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMouseByMouseIds(String[] mouseIds);
}

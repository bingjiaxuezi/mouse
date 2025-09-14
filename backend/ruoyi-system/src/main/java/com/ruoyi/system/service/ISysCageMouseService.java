package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysCageMouse;

/**
 * 笼子-小鼠关系Service接口
 * 
 * @author lczj
 * @date 2025-09-14
 */
public interface ISysCageMouseService 
{
    /**
     * 查询笼子-小鼠关系
     * 
     * @param cageMouseId 笼子-小鼠关系主键
     * @return 笼子-小鼠关系
     */
    public SysCageMouse selectSysCageMouseByCageMouseId(Long cageMouseId);

    /**
     * 查询笼子-小鼠关系列表
     * 
     * @param sysCageMouse 笼子-小鼠关系
     * @return 笼子-小鼠关系集合
     */
    public List<SysCageMouse> selectSysCageMouseList(SysCageMouse sysCageMouse);

    /**
     * 新增笼子-小鼠关系
     * 
     * @param sysCageMouse 笼子-小鼠关系
     * @return 结果
     */
    public int insertSysCageMouse(SysCageMouse sysCageMouse);

    /**
     * 修改笼子-小鼠关系
     * 
     * @param sysCageMouse 笼子-小鼠关系
     * @return 结果
     */
    public int updateSysCageMouse(SysCageMouse sysCageMouse);

    /**
     * 批量删除笼子-小鼠关系
     * 
     * @param cageMouseIds 需要删除的笼子-小鼠关系主键集合
     * @return 结果
     */
    public int deleteSysCageMouseByCageMouseIds(String cageMouseIds);

    /**
     * 删除笼子-小鼠关系信息
     * 
     * @param cageMouseId 笼子-小鼠关系主键
     * @return 结果
     */
    public int deleteSysCageMouseByCageMouseId(Long cageMouseId);
}

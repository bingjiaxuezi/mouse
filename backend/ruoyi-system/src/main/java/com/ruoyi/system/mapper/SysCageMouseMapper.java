package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysCageMouse;

/**
 * 笼子-小鼠关系Mapper接口
 * 
 * @author lczj
 * @date 2025-09-14
 */
public interface SysCageMouseMapper 
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
     * 删除笼子-小鼠关系
     * 
     * @param cageMouseId 笼子-小鼠关系主键
     * @return 结果
     */
    public int deleteSysCageMouseByCageMouseId(Long cageMouseId);

    /**
     * 批量删除笼子-小鼠关系
     * 
     * @param cageMouseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCageMouseByCageMouseIds(String[] cageMouseIds);
}

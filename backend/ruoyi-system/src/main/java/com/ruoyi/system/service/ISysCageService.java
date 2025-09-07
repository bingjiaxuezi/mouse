package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysCage;

/**
 * 笼子管理Service接口
 * 
 * @author lczj
 * @date 2025-09-07
 */
public interface ISysCageService 
{
    /**
     * 查询笼子管理
     * 
     * @param cageId 笼子管理主键
     * @return 笼子管理
     */
    public SysCage selectSysCageByCageId(Long cageId);

    /**
     * 查询笼子管理列表
     * 
     * @param sysCage 笼子管理
     * @return 笼子管理集合
     */
    public List<SysCage> selectSysCageList(SysCage sysCage);

    /**
     * 新增笼子管理
     * 
     * @param sysCage 笼子管理
     * @return 结果
     */
    public int insertSysCage(SysCage sysCage);

    /**
     * 修改笼子管理
     * 
     * @param sysCage 笼子管理
     * @return 结果
     */
    public int updateSysCage(SysCage sysCage);

    /**
     * 批量删除笼子管理
     * 
     * @param cageIds 需要删除的笼子管理主键集合
     * @return 结果
     */
    public int deleteSysCageByCageIds(String cageIds);

    /**
     * 删除笼子管理信息
     * 
     * @param cageId 笼子管理主键
     * @return 结果
     */
    public int deleteSysCageByCageId(Long cageId);
}

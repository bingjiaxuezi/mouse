package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCageMapper;
import com.ruoyi.system.domain.SysCage;
import com.ruoyi.system.service.ISysCageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 笼子管理Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-07
 */
@Service
public class SysCageServiceImpl implements ISysCageService 
{
    @Autowired
    private SysCageMapper sysCageMapper;

    /**
     * 查询笼子管理
     * 
     * @param cageId 笼子管理主键
     * @return 笼子管理
     */
    @Override
    public SysCage selectSysCageByCageId(Long cageId)
    {
        return sysCageMapper.selectSysCageByCageId(cageId);
    }

    /**
     * 查询笼子管理列表
     * 
     * @param sysCage 笼子管理
     * @return 笼子管理
     */
    @Override
    public List<SysCage> selectSysCageList(SysCage sysCage)
    {
        return sysCageMapper.selectSysCageList(sysCage);
    }

    /**
     * 新增笼子管理
     * 
     * @param sysCage 笼子管理
     * @return 结果
     */
    @Override
    public int insertSysCage(SysCage sysCage)
    {
        return sysCageMapper.insertSysCage(sysCage);
    }

    /**
     * 修改笼子管理
     * 
     * @param sysCage 笼子管理
     * @return 结果
     */
    @Override
    public int updateSysCage(SysCage sysCage)
    {
        return sysCageMapper.updateSysCage(sysCage);
    }

    /**
     * 批量删除笼子管理
     * 
     * @param cageIds 需要删除的笼子管理主键
     * @return 结果
     */
    @Override
    public int deleteSysCageByCageIds(String cageIds)
    {
        return sysCageMapper.deleteSysCageByCageIds(Convert.toStrArray(cageIds));
    }

    /**
     * 删除笼子管理信息
     * 
     * @param cageId 笼子管理主键
     * @return 结果
     */
    @Override
    public int deleteSysCageByCageId(Long cageId)
    {
        return sysCageMapper.deleteSysCageByCageId(cageId);
    }

    /**
     * 批量更新笼子状态
     * 
     * @param cageIds 笼子ID字符串，逗号分隔
     * @param status 新状态
     * @return 结果
     */
    @Override
    public int updateCageStatus(String cageIds, String status)
    {
        return sysCageMapper.updateCageStatus(cageIds, status);
    }
}

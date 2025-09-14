package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCageMouseMapper;
import com.ruoyi.system.domain.SysCageMouse;
import com.ruoyi.system.service.ISysCageMouseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 笼子-小鼠关系Service业务层处理
 * 
 * @author lczj
 * @date 2025-09-14
 */
@Service
public class SysCageMouseServiceImpl implements ISysCageMouseService 
{
    @Autowired
    private SysCageMouseMapper sysCageMouseMapper;

    /**
     * 查询笼子-小鼠关系
     * 
     * @param cageMouseId 笼子-小鼠关系主键
     * @return 笼子-小鼠关系
     */
    @Override
    public SysCageMouse selectSysCageMouseByCageMouseId(Long cageMouseId)
    {
        return sysCageMouseMapper.selectSysCageMouseByCageMouseId(cageMouseId);
    }

    /**
     * 查询笼子-小鼠关系列表
     * 
     * @param sysCageMouse 笼子-小鼠关系
     * @return 笼子-小鼠关系
     */
    @Override
    public List<SysCageMouse> selectSysCageMouseList(SysCageMouse sysCageMouse)
    {
        return sysCageMouseMapper.selectSysCageMouseList(sysCageMouse);
    }

    /**
     * 新增笼子-小鼠关系
     * 
     * @param sysCageMouse 笼子-小鼠关系
     * @return 结果
     */
    @Override
    public int insertSysCageMouse(SysCageMouse sysCageMouse)
    {
        sysCageMouse.setCreateTime(DateUtils.getNowDate());
        return sysCageMouseMapper.insertSysCageMouse(sysCageMouse);
    }

    /**
     * 修改笼子-小鼠关系
     * 
     * @param sysCageMouse 笼子-小鼠关系
     * @return 结果
     */
    @Override
    public int updateSysCageMouse(SysCageMouse sysCageMouse)
    {
        sysCageMouse.setUpdateTime(DateUtils.getNowDate());
        return sysCageMouseMapper.updateSysCageMouse(sysCageMouse);
    }

    /**
     * 批量删除笼子-小鼠关系
     * 
     * @param cageMouseIds 需要删除的笼子-小鼠关系主键
     * @return 结果
     */
    @Override
    public int deleteSysCageMouseByCageMouseIds(String cageMouseIds)
    {
        return sysCageMouseMapper.deleteSysCageMouseByCageMouseIds(Convert.toStrArray(cageMouseIds));
    }

    /**
     * 删除笼子-小鼠关系信息
     * 
     * @param cageMouseId 笼子-小鼠关系主键
     * @return 结果
     */
    @Override
    public int deleteSysCageMouseByCageMouseId(Long cageMouseId)
    {
        return sysCageMouseMapper.deleteSysCageMouseByCageMouseId(cageMouseId);
    }
}

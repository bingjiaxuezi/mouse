package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TMouse;
import com.ruoyi.system.domain.dto.TMouseDTO;
import com.ruoyi.system.domain.vo.TMouseVO;
import com.ruoyi.system.mapper.TMouseMapper;
import com.ruoyi.system.service.ITMouseService;
import com.ruoyi.system.service.ITPhysiologicalStateService;
import com.ruoyi.system.service.ITPositionTrackService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 老鼠Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Service
@Slf4j
public class TMouseServiceImpl extends ServiceImpl<TMouseMapper, TMouse> implements ITMouseService {
    
    // 注意：继承ServiceImpl后，可以直接使用baseMapper，无需单独注入
    // @Autowired
    // private TMouseMapper tMouseMapper;
    
    @Autowired
    private ITPhysiologicalStateService tPhysiologicalStateService;
    
    @Autowired
    private ITPositionTrackService tPositionTrackService;

    /**
     * 查询老鼠
     * 
     * @param id 老鼠主键
     * @return 老鼠
     */
    @Override
    public TMouseVO selectTMouseById(Long id) {
        TMouseVO tMouseVO = baseMapper.selectTMouseById(id);
        return tMouseVO;
    }

    /**
     * 查询老鼠列表
     * 
     * @param tMouse 老鼠
     * @return 老鼠
     */
    @Override
    public List<TMouseVO> selectTMouseList(TMouse tMouse) {
        TMouse tMouseQuery = new TMouse();
        if(StringUtils.isNotNull(tMouse.getMouseCode())){
            tMouseQuery.setMouseCode(tMouse.getMouseCode());
        }
        if(StringUtils.isNotNull(tMouse.getMouseName())){
            tMouseQuery.setMouseName(tMouse.getMouseName());
        }
        if(StringUtils.isNotNull(tMouse.getSpecies())){
            tMouseQuery.setSpecies(tMouse.getSpecies());
        }
        if(StringUtils.isNotNull(tMouse.getGender())){
            tMouseQuery.setGender(tMouse.getGender());
        }
        if(StringUtils.isNotNull(tMouse.getStatus())){
            tMouseQuery.setStatus(tMouse.getStatus());
        }
        tMouseQuery.setUpdateTime(DateUtils.getNowDate());
        tMouseQuery.setUpdateTime(DateUtils.getNowDate());
        return baseMapper.selectTMouseList(tMouseQuery);
    }

    /**
     * 新增老鼠
     * 
     * @param tMouseDTO 老鼠
     * @return 结果
     */
    @Override
    public int insertTMouse(TMouseDTO tMouseDTO) {
        TMouse tMouse = new TMouse();
        BeanUtils.copyProperties(tMouseDTO, tMouse);
        
        // 如果小鼠编号为空，自动生成UUID
        if (tMouse.getMouseCode() == null || tMouse.getMouseCode().trim().isEmpty()) {
            tMouse.setMouseCode(java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 16));
        }
        
        tMouse.setCreateTime(DateUtils.getNowDate());
        return baseMapper.insertTMouse(tMouse); // 使用MyBatis-Plus的insert方法
    }

    /**
     * 修改老鼠
     * 
     * @param tMouseDTO 老鼠
     * @return 结果
     */
    @Override
    public int updateTMouse(TMouseDTO tMouseDTO) {
        TMouse tMouse = new TMouse();
        BeanUtils.copyProperties(tMouseDTO, tMouse);
        tMouse.setUpdateTime(DateUtils.getNowDate());
        log.error(tMouse.toString());
        return baseMapper.updateTMouse(tMouse); // 使用MyBatis-Plus的updateById方法
    }

    /**
     * 批量删除老鼠
     * 
     * @param ids 需要删除的老鼠主键
     * @return 结果
     */
    @Override
    public int deleteTMouseByIds(Long[] ids) {
        // 先删除相关的生理状态数据和位置追踪数据
        for (Long id : ids) {
            TMouseVO mouse = baseMapper.selectTMouseById(id);
            if (mouse != null && StringUtils.isNotEmpty(mouse.getMouseCode())) {
                tPhysiologicalStateService.deleteTPhysiologicalStateByMouseCode(mouse.getMouseCode());
                tPositionTrackService.deleteTPositionTrackByMouseCode(mouse.getMouseCode());
                log.info("删除小鼠 {} 的生理状态数据和位置追踪数据", mouse.getMouseCode());
            }
        }
        // 批量删除小鼠 - 使用MyBatis-Plus的批量删除方法
        return baseMapper.deleteTMouseByIds(ids);
    }

    /**
     * 删除老鼠信息
     * 
     * @param id 老鼠主键
     * @return 结果
     */
    @Override
    public int deleteTMouseById(Long id) {
        // 先获取小鼠信息
        TMouseVO mouse = baseMapper.selectTMouseById(id);
        if (mouse != null && StringUtils.isNotEmpty(mouse.getMouseCode())) {
            // 删除相关的生理状态数据和位置追踪数据
            tPhysiologicalStateService.deleteTPhysiologicalStateByMouseCode(mouse.getMouseCode());
            tPositionTrackService.deleteTPositionTrackByMouseCode(mouse.getMouseCode());
            log.info("删除小鼠 {} 的生理状态数据和位置追踪数据", mouse.getMouseCode());
        }
        // 删除小鼠 - 使用MyBatis-Plus的deleteById方法
        return baseMapper.deleteTMouseById(id);
    }

    /**
     * 校验老鼠编号是否唯一
     * 
     * @param mouseCode 老鼠编号
     * @return 结果
     */
    @Override
    public String checkMouseCodeUnique(String mouseCode) {
        TMouse mouse = baseMapper.checkMouseCodeUnique(mouseCode);
        if (StringUtils.isNotNull(mouse)) {
            return "1";
        }
        return "0";
    }

    /**
     * 根据老鼠编号查询老鼠信息
     * 
     * @param mouseCode 老鼠编号
     * @return 老鼠信息
     */
    @Override
    public TMouseVO selectTMouseByMouseCode(String mouseCode) {
        return baseMapper.selectTMouseByMouseCode(mouseCode);
    }
}
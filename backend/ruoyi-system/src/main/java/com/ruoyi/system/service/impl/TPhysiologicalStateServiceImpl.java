package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TPhysiologicalState;
import com.ruoyi.system.domain.dto.TPhysiologicalStateDTO;
import com.ruoyi.system.domain.vo.TPhysiologicalStateVO;
import com.ruoyi.system.mapper.TPhysiologicalStateMapper;
import com.ruoyi.system.service.ITPhysiologicalStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生理状态Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Service
public class TPhysiologicalStateServiceImpl implements ITPhysiologicalStateService {
    
    @Autowired
    private TPhysiologicalStateMapper tPhysiologicalStateMapper;

    /**
     * 查询生理状态
     * 
     * @param id 生理状态主键
     * @return 生理状态
     */
    @Override
    public TPhysiologicalStateVO selectTPhysiologicalStateById(Long id) {
        return tPhysiologicalStateMapper.selectTPhysiologicalStateById(id);
    }

    /**
     * 查询生理状态列表
     * 
     * @param tPhysiologicalState 生理状态
     * @return 生理状态
     */
    @Override
    public List<TPhysiologicalStateVO> selectTPhysiologicalStateList(TPhysiologicalState tPhysiologicalState) {
        return tPhysiologicalStateMapper.selectTPhysiologicalStateList(tPhysiologicalState);
    }

    /**
     * 新增生理状态
     * 
     * @param tPhysiologicalStateDTO 生理状态
     * @return 结果
     */
    @Override
    public int insertTPhysiologicalState(TPhysiologicalStateDTO tPhysiologicalStateDTO) {
        TPhysiologicalState tPhysiologicalState = new TPhysiologicalState();
        BeanUtils.copyProperties(tPhysiologicalStateDTO, tPhysiologicalState);
        tPhysiologicalState.setCreateTime(DateUtils.getNowDate());
        return tPhysiologicalStateMapper.insertTPhysiologicalState(tPhysiologicalState);
    }

    /**
     * 修改生理状态
     * 
     * @param tPhysiologicalStateDTO 生理状态
     * @return 结果
     */
    @Override
    public int updateTPhysiologicalState(TPhysiologicalStateDTO tPhysiologicalStateDTO) {
        TPhysiologicalState tPhysiologicalState = new TPhysiologicalState();
        BeanUtils.copyProperties(tPhysiologicalStateDTO, tPhysiologicalState);
        return tPhysiologicalStateMapper.updateTPhysiologicalState(tPhysiologicalState);
    }

    /**
     * 批量删除生理状态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    @Override
    public int deleteTPhysiologicalStateByIds(Long[] ids) {
        return tPhysiologicalStateMapper.deleteTPhysiologicalStateByIds(ids);
    }

    /**
     * 删除生理状态信息
     * 
     * @param id 生理状态主键
     * @return 结果
     */
    @Override
    public int deleteTPhysiologicalStateById(Long id) {
        return tPhysiologicalStateMapper.deleteTPhysiologicalStateById(id);
    }
    
    /**
     * 根据小鼠编码删除生理状态数据
     * 
     * @param mouseCode 小鼠编码
     * @return 结果
     */
    @Override
    public int deleteTPhysiologicalStateByMouseCode(String mouseCode) {
        return tPhysiologicalStateMapper.deleteTPhysiologicalStateByMouseCode(mouseCode);
    }
}
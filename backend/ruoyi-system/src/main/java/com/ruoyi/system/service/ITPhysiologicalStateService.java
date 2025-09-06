package com.ruoyi.system.service;

import com.ruoyi.system.domain.TPhysiologicalState;
import com.ruoyi.system.domain.dto.TPhysiologicalStateDTO;
import com.ruoyi.system.domain.vo.TPhysiologicalStateVO;

import java.util.List;

/**
 * 生理状态Service接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface ITPhysiologicalStateService {
    
    /**
     * 查询生理状态
     * 
     * @param id 生理状态主键
     * @return 生理状态
     */
    public TPhysiologicalStateVO selectTPhysiologicalStateById(Long id);

    /**
     * 查询生理状态列表
     * 
     * @param tPhysiologicalState 生理状态
     * @return 生理状态集合
     */
    public List<TPhysiologicalStateVO> selectTPhysiologicalStateList(TPhysiologicalState tPhysiologicalState);

    /**
     * 新增生理状态
     * 
     * @param tPhysiologicalStateDTO 生理状态
     * @return 结果
     */
    public int insertTPhysiologicalState(TPhysiologicalStateDTO tPhysiologicalStateDTO);

    /**
     * 修改生理状态
     * 
     * @param tPhysiologicalStateDTO 生理状态
     * @return 结果
     */
    public int updateTPhysiologicalState(TPhysiologicalStateDTO tPhysiologicalStateDTO);

    /**
     * 批量删除生理状态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPhysiologicalStateByIds(Long[] ids);

    /**
     * 删除生理状态信息
     * 
     * @param id 生理状态主键
     * @return 结果
     */
    public int deleteTPhysiologicalStateById(Long id);
    
    /**
     * 根据小鼠编码删除生理状态数据
     * 
     * @param mouseCode 小鼠编码
     * @return 结果
     */
    public int deleteTPhysiologicalStateByMouseCode(String mouseCode);
}
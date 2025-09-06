package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TPhysiologicalState;
import com.ruoyi.system.domain.vo.TPhysiologicalStateVO;

import java.util.List;

/**
 * 生理状态Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface TPhysiologicalStateMapper {
    
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
     * @param tPhysiologicalState 生理状态
     * @return 结果
     */
    public int insertTPhysiologicalState(TPhysiologicalState tPhysiologicalState);

    /**
     * 修改生理状态
     * 
     * @param tPhysiologicalState 生理状态
     * @return 结果
     */
    public int updateTPhysiologicalState(TPhysiologicalState tPhysiologicalState);

    /**
     * 删除生理状态
     * 
     * @param id 生理状态主键
     * @return 结果
     */
    public int deleteTPhysiologicalStateById(Long id);

    /**
     * 批量删除生理状态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPhysiologicalStateByIds(Long[] ids);
    
    /**
     * 根据小鼠编码删除生理状态数据
     * 
     * @param mouseCode 小鼠编码
     * @return 结果
     */
    public int deleteTPhysiologicalStateByMouseCode(String mouseCode);
}
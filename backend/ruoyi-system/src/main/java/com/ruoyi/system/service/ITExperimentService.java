package com.ruoyi.system.service;

import com.ruoyi.system.domain.TExperiment;
import com.ruoyi.system.domain.dto.TExperimentDTO;
import com.ruoyi.system.domain.vo.TExperimentVO;

import java.util.List;

/**
 * 实验Service接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface ITExperimentService {
    
    /**
     * 查询实验
     * 
     * @param id 实验主键
     * @return 实验
     */
    public TExperimentVO selectTExperimentById(Long id);

    /**
     * 查询实验列表
     * 
     * @param tExperiment 实验
     * @return 实验集合
     */
    public List<TExperimentVO> selectTExperimentList(TExperiment tExperiment);

    /**
     * 新增实验
     * 
     * @param tExperimentDTO 实验
     * @return 结果
     */
    public int insertTExperiment(TExperimentDTO tExperimentDTO);

    /**
     * 修改实验
     * 
     * @param tExperimentDTO 实验
     * @return 结果
     */
    public int updateTExperiment(TExperimentDTO tExperimentDTO);

    /**
     * 批量删除实验
     * 
     * @param ids 需要删除的实验主键集合
     * @return 结果
     */
    public int deleteTExperimentByIds(Long[] ids);

    /**
     * 删除实验信息
     * 
     * @param id 实验主键
     * @return 结果
     */
    public int deleteTExperimentById(Long id);

    /**
     * 校验实验编号是否唯一
     * 
     * @param experimentCode 实验编号
     * @return 结果
     */
    public String checkExperimentCodeUnique(String experimentCode);
}
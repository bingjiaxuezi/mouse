package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TExperiment;
import com.ruoyi.system.domain.vo.TExperimentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 实验Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface TExperimentMapper {
    
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
     * @param tExperiment 实验
     * @return 结果
     */
    public int insertTExperiment(TExperiment tExperiment);

    /**
     * 修改实验
     * 
     * @param tExperiment 实验
     * @return 结果
     */
    public int updateTExperiment(TExperiment tExperiment);

    /**
     * 删除实验
     * 
     * @param id 实验主键
     * @return 结果
     */
    public int deleteTExperimentById(Long id);

    /**
     * 批量删除实验
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTExperimentByIds(Long[] ids);

    /**
     * 校验实验编号是否唯一
     * 
     * @param experimentCode 实验编号
     * @return 结果
     */
    public TExperiment checkExperimentCodeUnique(@Param("experimentCode") String experimentCode);
}
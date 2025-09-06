package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TExperiment;
import com.ruoyi.system.domain.dto.TExperimentDTO;
import com.ruoyi.system.domain.vo.TExperimentVO;
import com.ruoyi.system.mapper.TExperimentMapper;
import com.ruoyi.system.service.ITExperimentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实验Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
@Service
public class TExperimentServiceImpl implements ITExperimentService {
    
    @Autowired
    private TExperimentMapper tExperimentMapper;

    /**
     * 查询实验
     * 
     * @param id 实验主键
     * @return 实验
     */
    @Override
    public TExperimentVO selectTExperimentById(Long id) {
        return tExperimentMapper.selectTExperimentById(id);
    }

    /**
     * 查询实验列表
     * 
     * @param tExperiment 实验
     * @return 实验
     */
    @Override
    public List<TExperimentVO> selectTExperimentList(TExperiment tExperiment) {
        return tExperimentMapper.selectTExperimentList(tExperiment);
    }

    /**
     * 新增实验
     * 
     * @param tExperimentDTO 实验
     * @return 结果
     */
    @Override
    public int insertTExperiment(TExperimentDTO tExperimentDTO) {
        TExperiment tExperiment = new TExperiment();
        BeanUtils.copyProperties(tExperimentDTO, tExperiment);
        tExperiment.setCreateTime(DateUtils.getNowDate());
        return tExperimentMapper.insertTExperiment(tExperiment);
    }

    /**
     * 修改实验
     * 
     * @param tExperimentDTO 实验
     * @return 结果
     */
    @Override
    public int updateTExperiment(TExperimentDTO tExperimentDTO) {
        TExperiment tExperiment = new TExperiment();
        BeanUtils.copyProperties(tExperimentDTO, tExperiment);
        tExperiment.setUpdateTime(DateUtils.getNowDate());
        return tExperimentMapper.updateTExperiment(tExperiment);
    }

    /**
     * 批量删除实验
     * 
     * @param ids 需要删除的实验主键
     * @return 结果
     */
    @Override
    public int deleteTExperimentByIds(Long[] ids) {
        return tExperimentMapper.deleteTExperimentByIds(ids);
    }

    /**
     * 删除实验信息
     * 
     * @param id 实验主键
     * @return 结果
     */
    @Override
    public int deleteTExperimentById(Long id) {
        return tExperimentMapper.deleteTExperimentById(id);
    }

    /**
     * 校验实验编号是否唯一
     * 
     * @param experimentCode 实验编号
     * @return 结果
     */
    @Override
    public String checkExperimentCodeUnique(String experimentCode) {
        TExperiment experiment = tExperimentMapper.checkExperimentCodeUnique(experimentCode);
        if (StringUtils.isNotNull(experiment)) {
            return "1";
        }
        return "0";
    }
}
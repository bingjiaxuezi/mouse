package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysExperimentTemplateMapper;
import com.ruoyi.system.domain.SysExperimentTemplate;
import com.ruoyi.system.service.ISysExperimentTemplateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 实验模板Service业务层处理
 * 
 * @author lichenzijia
 * @date 2025-09-07
 */
@Service
public class SysExperimentTemplateServiceImpl implements ISysExperimentTemplateService 
{
    @Autowired
    private SysExperimentTemplateMapper sysExperimentTemplateMapper;

    /**
     * 查询实验模板
     * 
     * @param templateId 实验模板主键
     * @return 实验模板
     */
    @Override
    public SysExperimentTemplate selectSysExperimentTemplateByTemplateId(Long templateId)
    {
        return sysExperimentTemplateMapper.selectSysExperimentTemplateByTemplateId(templateId);
    }

    /**
     * 查询实验模板列表
     * 
     * @param sysExperimentTemplate 实验模板
     * @return 实验模板
     */
    @Override
    public List<SysExperimentTemplate> selectSysExperimentTemplateList(SysExperimentTemplate sysExperimentTemplate)
    {
        return sysExperimentTemplateMapper.selectSysExperimentTemplateList(sysExperimentTemplate);
    }

    /**
     * 新增实验模板
     * 
     * @param sysExperimentTemplate 实验模板
     * @return 结果
     */
    @Override
    public int insertSysExperimentTemplate(SysExperimentTemplate sysExperimentTemplate)
    {
        return sysExperimentTemplateMapper.insertSysExperimentTemplate(sysExperimentTemplate);
    }

    /**
     * 修改实验模板
     * 
     * @param sysExperimentTemplate 实验模板
     * @return 结果
     */
    @Override
    public int updateSysExperimentTemplate(SysExperimentTemplate sysExperimentTemplate)
    {
        return sysExperimentTemplateMapper.updateSysExperimentTemplate(sysExperimentTemplate);
    }

    /**
     * 批量删除实验模板
     * 
     * @param templateIds 需要删除的实验模板主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentTemplateByTemplateIds(String templateIds)
    {
        return sysExperimentTemplateMapper.deleteSysExperimentTemplateByTemplateIds(Convert.toStrArray(templateIds));
    }

    /**
     * 删除实验模板信息
     * 
     * @param templateId 实验模板主键
     * @return 结果
     */
    @Override
    public int deleteSysExperimentTemplateByTemplateId(Long templateId)
    {
        return sysExperimentTemplateMapper.deleteSysExperimentTemplateByTemplateId(templateId);
    }
}

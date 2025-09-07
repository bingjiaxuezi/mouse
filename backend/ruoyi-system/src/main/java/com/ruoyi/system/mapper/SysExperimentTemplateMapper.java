package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysExperimentTemplate;

/**
 * 实验模板Mapper接口
 * 
 * @author lichenzijia
 * @date 2025-09-07
 */
public interface SysExperimentTemplateMapper 
{
    /**
     * 查询实验模板
     * 
     * @param templateId 实验模板主键
     * @return 实验模板
     */
    public SysExperimentTemplate selectSysExperimentTemplateByTemplateId(Long templateId);

    /**
     * 查询实验模板列表
     * 
     * @param sysExperimentTemplate 实验模板
     * @return 实验模板集合
     */
    public List<SysExperimentTemplate> selectSysExperimentTemplateList(SysExperimentTemplate sysExperimentTemplate);

    /**
     * 新增实验模板
     * 
     * @param sysExperimentTemplate 实验模板
     * @return 结果
     */
    public int insertSysExperimentTemplate(SysExperimentTemplate sysExperimentTemplate);

    /**
     * 修改实验模板
     * 
     * @param sysExperimentTemplate 实验模板
     * @return 结果
     */
    public int updateSysExperimentTemplate(SysExperimentTemplate sysExperimentTemplate);

    /**
     * 删除实验模板
     * 
     * @param templateId 实验模板主键
     * @return 结果
     */
    public int deleteSysExperimentTemplateByTemplateId(Long templateId);

    /**
     * 批量删除实验模板
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExperimentTemplateByTemplateIds(String[] templateIds);
}

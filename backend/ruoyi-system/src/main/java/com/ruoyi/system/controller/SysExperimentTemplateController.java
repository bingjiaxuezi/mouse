package com.ruoyi.system.controller;

import java.util.List;
import java.util.Date;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysExperimentTemplate;
import com.ruoyi.system.domain.SysExperiment;
import com.ruoyi.system.service.ISysExperimentTemplateService;
import com.ruoyi.system.service.ISysExperimentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 实验模板Controller
 * 
 * @author lichenzijia
 * @date 2025-09-07
 */
@Controller
@RequestMapping("/system/SysExperimentTemplate")
public class SysExperimentTemplateController extends BaseController
{
    private String prefix = "system/SysExperimentTemplate";

    @Autowired
    private ISysExperimentTemplateService sysExperimentTemplateService;
    
    @Autowired
    private ISysExperimentService sysExperimentService;

    @RequiresPermissions("system:SysExperimentTemplate:view")
    @GetMapping()
    public String SysExperimentTemplate()
    {
        return prefix + "/SysExperimentTemplate";
    }

    /**
     * 查询实验模板列表
     */
    @RequiresPermissions("system:SysExperimentTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysExperimentTemplate sysExperimentTemplate)
    {
        startPage();
        List<SysExperimentTemplate> list = sysExperimentTemplateService.selectSysExperimentTemplateList(sysExperimentTemplate);
        return getDataTable(list);
    }

    /**
     * 导出实验模板列表
     */
    @RequiresPermissions("system:SysExperimentTemplate:export")
    @Log(title = "实验模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysExperimentTemplate sysExperimentTemplate)
    {
        List<SysExperimentTemplate> list = sysExperimentTemplateService.selectSysExperimentTemplateList(sysExperimentTemplate);
        ExcelUtil<SysExperimentTemplate> util = new ExcelUtil<SysExperimentTemplate>(SysExperimentTemplate.class);
        return util.exportExcel(list, "实验模板数据");
    }

    /**
     * 新增实验模板
     */
    @RequiresPermissions("system:SysExperimentTemplate:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实验模板
     */
    @RequiresPermissions("system:SysExperimentTemplate:add")
    @Log(title = "实验模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysExperimentTemplate sysExperimentTemplate)
    {
        return toAjax(sysExperimentTemplateService.insertSysExperimentTemplate(sysExperimentTemplate));
    }

    /**
     * 修改实验模板
     */
    @RequiresPermissions("system:SysExperimentTemplate:edit")
    @GetMapping("/edit/{templateId}")
    public String edit(@PathVariable("templateId") Long templateId, ModelMap mmap)
    {
        SysExperimentTemplate sysExperimentTemplate = sysExperimentTemplateService.selectSysExperimentTemplateByTemplateId(templateId);
        mmap.put("sysExperimentTemplate", sysExperimentTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存实验模板
     */
    @RequiresPermissions("system:SysExperimentTemplate:edit")
    @Log(title = "实验模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysExperimentTemplate sysExperimentTemplate)
    {
        return toAjax(sysExperimentTemplateService.updateSysExperimentTemplate(sysExperimentTemplate));
    }

    /**
     * 删除实验模板
     */
    @RequiresPermissions("system:SysExperimentTemplate:remove")
    @Log(title = "实验模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysExperimentTemplateService.deleteSysExperimentTemplateByTemplateIds(ids));
    }
    
    /**
     * 保存实验为模板
     */
    @RequiresPermissions("system:SysExperimentTemplate:add")
    @Log(title = "保存实验为模板", businessType = BusinessType.INSERT)
    @PostMapping("/saveAsTemplate")
    @ResponseBody
    public AjaxResult saveAsTemplate(@RequestParam("experimentId") Long experimentId, 
                                   @RequestParam("templateName") String templateName)
    {
        try {
            // 查询实验信息
            SysExperiment experiment = sysExperimentService.selectSysExperimentByExperimentId(experimentId);
            if (experiment == null) {
                return AjaxResult.error("实验不存在");
            }
            
            // 创建模板对象
            SysExperimentTemplate template = new SysExperimentTemplate();
            template.setTemplateName(templateName);
            template.setTemplateCode("TPL" + System.currentTimeMillis()); // 生成唯一模板编码
            template.setTemplateType(experiment.getExperimentType());
            template.setTemplateDesc("从实验[" + experiment.getExperimentName() + "]保存的模板");
            template.setTemplateStatus("1"); // 启用状态
            
            // 计算实验周期
            if (experiment.getPlannedBeginTime() != null && experiment.getPlannedFinishTime() != null) {
                long duration = (experiment.getPlannedFinishTime().getTime() - experiment.getPlannedBeginTime().getTime()) / (1000 * 60 * 60 * 24);
                template.setDefaultDuration(duration);
            }
            
            // 构建配置参数JSON
            ObjectMapper mapper = new ObjectMapper();
            java.util.Map<String, Object> config = new java.util.HashMap<>();
            config.put("experimentType", experiment.getExperimentType());
            config.put("experimentObjective", experiment.getExperimentObjective());
            config.put("experimentDesc", experiment.getExperimentDesc());
            config.put("principalResearcher", experiment.getPrincipalResearcher());
            config.put("coResearchers", experiment.getCoResearchers());
            config.put("experimentTeam", experiment.getExperimentTeam());
            config.put("experimentConfig", experiment.getExperimentConfig());

            
            template.setDefaultConfig(mapper.writeValueAsString(config));
            
            // 保存模板
            int result = sysExperimentTemplateService.insertSysExperimentTemplate(template);
            return toAjax(result);
            
        } catch (JsonProcessingException e) {
            return AjaxResult.error("JSON处理异常: " + e.getMessage());
        } catch (Exception e) {
            return AjaxResult.error("保存模板失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取所有启用的模板列表（用于下拉选择）
     */
    @RequiresPermissions("system:SysExperimentTemplate:list")
    @PostMapping("/getActiveTemplates")
    @ResponseBody
    public AjaxResult getActiveTemplates()
    {
        SysExperimentTemplate queryTemplate = new SysExperimentTemplate();
        queryTemplate.setTemplateStatus("1"); // 只查询启用的模板
        List<SysExperimentTemplate> list = sysExperimentTemplateService.selectSysExperimentTemplateList(queryTemplate);
        return AjaxResult.success(list);
    }
}

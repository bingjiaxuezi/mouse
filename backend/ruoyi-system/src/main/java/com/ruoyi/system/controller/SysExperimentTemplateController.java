package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysExperimentTemplate;
import com.ruoyi.system.service.ISysExperimentTemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
}
